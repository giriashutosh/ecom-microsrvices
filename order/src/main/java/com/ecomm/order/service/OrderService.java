package com.ecomm.order.service;

import com.ecomm.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(Long userId);
}
