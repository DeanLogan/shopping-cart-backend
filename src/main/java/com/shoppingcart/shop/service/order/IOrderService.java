package com.shoppingcart.shop.service.order;

import com.shoppingcart.shop.dto.OrderDto;
import com.shoppingcart.shop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
