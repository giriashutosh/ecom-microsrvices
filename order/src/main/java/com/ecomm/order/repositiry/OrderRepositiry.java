package com.ecomm.order.repositiry;

import com.ecomm.order.Entity.OrderPlaced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositiry extends JpaRepository<OrderPlaced, Long> {
}
