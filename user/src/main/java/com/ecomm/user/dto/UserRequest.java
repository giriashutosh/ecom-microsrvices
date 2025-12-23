package com.ecomm.user.dto;

import com.ecomm.user.Entity.Address;
import lombok.Data;

@Data
public class UserRequest {
    //private Long id;
    private String name;
    private int age;
    private String phone_no;
    private String email;
    private Address address;
}
