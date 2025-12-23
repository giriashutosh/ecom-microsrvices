package com.ecomm.order.controller;

import com.ecomm.order.dto.OrderResponse;
import com.ecomm.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/placed/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable Long userId){
        OrderResponse response = orderService.placeOrder(userId);
        return ResponseEntity.ok(response);
    }
}
