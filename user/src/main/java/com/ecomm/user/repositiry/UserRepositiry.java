package com.ecomm.user.repositiry;

import com.ecomm.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositiry extends JpaRepository<User, Long> {
}
