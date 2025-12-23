package com.ecomm.order.repositiry;

import com.ecomm.order.Entity.CartItem;
//import com.ecomm.order.Entity.Product;
//import com.ecomm.order.Entity.UserCart;
import com.ecomm.order.Entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepositiry extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserCartAndProductId(UserCart cart, Long productId);
}
