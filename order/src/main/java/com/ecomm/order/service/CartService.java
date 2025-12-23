package com.ecomm.order.service;

import com.ecomm.order.Entity.UserCart;

public interface CartService {
    UserCart addToCart(Long userId, Long productId, int quantity);
    UserCart viewCart(Long userId);
    void removeItemFromCart(Long userId, Long productId);
}
