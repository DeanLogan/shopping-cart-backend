package com.shoppingcart.shop.service.repository;

import com.shoppingcart.shop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
