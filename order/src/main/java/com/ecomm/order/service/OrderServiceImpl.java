package com.ecomm.order.service;

import com.ecomm.order.Entity.*;
import com.ecomm.order.dto.OrderItemResponse;
import com.ecomm.order.dto.OrderResponse;
import com.ecomm.order.repositiry.CartItemRepositiry;
import com.ecomm.order.repositiry.OrderRepositiry;
import com.ecomm.order.repositiry.UserCartRepositiry;
//import com.ecomm.order.repositiry.UserRepositiry;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
//    private UserRepositiry userRepositiry;
    private UserCartRepositiry userCartRepositiry;
    private OrderRepositiry orderRepositiry;
    private CartItemRepositiry cartItemRepositiry;

    public OrderServiceImpl(
                            UserCartRepositiry userCartRepositiry,
                            OrderRepositiry orderRepositiry,
                            CartItemRepositiry cartItemRepositiry){
        this.userCartRepositiry = userCartRepositiry;
        this.orderRepositiry = orderRepositiry;
        this.cartItemRepositiry = cartItemRepositiry;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(Long userId) {
        //fetch or validate user
        //User user = userRepositiry.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        //fetch user cart
        UserCart cart = userCartRepositiry.findByUserId(userId).orElseThrow(() -> new RuntimeException("cart not exist"));

        if(cart.getItems().isEmpty()){
            throw new RuntimeException("cart is empty, order can not placed");
        }

        //create order
        OrderPlaced order = new OrderPlaced();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.PENDING);

        BigDecimal total = BigDecimal.ZERO;

        //convert cart items to order items
        for(CartItem item: cart.getItems()){
            OrderItem orderItem = new OrderItem();
            //orderItem.setId(item.getId());
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());

            order.getItems().add(orderItem);
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        //save order
        OrderPlaced savedOrder = orderRepositiry.save(order);

        //clear cart
        cartItemRepositiry.deleteAll(cart.getItems());
        cart.getItems().clear();
        userCartRepositiry.save(cart);

        //prepare response DTO
        List<OrderItemResponse> itemResponses = savedOrder.getItems()
                                              .stream()
                                               .map(item-> new OrderItemResponse(
                                                       item.getProductId(),
                                                       //item.getProductId().getName(),
                                                       item.getQuantity(),
                                                       item.getPrice()

                                               )).toList();


        return new OrderResponse(
                savedOrder.getId(),
//                user.getId(),
//                user.getName(),
                itemResponses,
                savedOrder.getOrderStatus(),
                savedOrder.getOrderDate(),
                total
        );
    }
}
