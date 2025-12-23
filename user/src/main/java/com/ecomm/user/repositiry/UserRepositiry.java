package com.ecomm.user.repositiry;

import com.ecomm.user.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositiry extends MongoRepository<User, String> {
}
