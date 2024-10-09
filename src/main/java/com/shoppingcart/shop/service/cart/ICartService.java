package com.shoppingcart.shop.service.cart;

import com.shoppingcart.shop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Long initialiseNewCart();

    Cart getCartByUserId(Long userId);
}
