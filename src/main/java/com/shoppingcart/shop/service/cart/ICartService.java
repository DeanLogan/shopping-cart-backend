package com.shoppingcart.shop.service.cart;

import com.shoppingcart.shop.model.Cart;
import com.shoppingcart.shop.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initialiseNewCart(User user);

    Cart getCartByUserId(Long userId);
}
