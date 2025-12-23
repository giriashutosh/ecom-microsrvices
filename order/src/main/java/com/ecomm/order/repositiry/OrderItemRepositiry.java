package com.ecomm.order.repositiry;

import com.ecomm.order.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepositiry extends JpaRepository<OrderItem,Long> {
}
