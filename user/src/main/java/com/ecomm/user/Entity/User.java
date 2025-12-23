package com.ecomm.user.Entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private String phone_no;

    @Indexed(unique = true)
    private String email;

    private UserRole role = UserRole.CUSTOMER;

    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
