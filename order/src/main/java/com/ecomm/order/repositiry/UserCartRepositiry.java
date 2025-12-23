package com.ecomm.order.repositiry;

//import com.ecom.springproject.Entity.User;
import com.ecomm.order.Entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCartRepositiry extends JpaRepository<UserCart, Long> {
    Optional<UserCart> findByUserId(Long userId);
}
