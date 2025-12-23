package com.ecomm.order.dto;

import com.ecomm.order.Entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
//    private Long userId;
//    private String userName;
    private List<OrderItemResponse> items;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
}
