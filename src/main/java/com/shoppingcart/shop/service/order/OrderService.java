package com.shoppingcart.shop.service.order;

import com.shoppingcart.shop.dto.OrderDto;
import com.shoppingcart.shop.enums.OrderStatus;
import com.shoppingcart.shop.exceptions.ResourceNotFoundException;
import com.shoppingcart.shop.model.Cart;
import com.shoppingcart.shop.model.Order;
import com.shoppingcart.shop.model.OrderItem;
import com.shoppingcart.shop.model.Product;
import com.shoppingcart.shop.service.cart.CartService;
import com.shoppingcart.shop.repository.OrderRepository;
import com.shoppingcart.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);

        List<OrderItem> ordetItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(ordetItemList));
        order.setOrderTotalAmount(calculateTotalAmount(ordetItemList));
        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getCartItems()
                .stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                    productRepository.save(product);
                    return new OrderItem(
                            order,
                            product,
                            cartItem.getQuantity(),
                            cartItem.getUnitPrice()
                    );
                }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository
                .findById(orderId)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
