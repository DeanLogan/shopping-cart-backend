package com.shoppingcart.shop.service.order;

import com.shoppingcart.shop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getOrders(Long userId);
}
