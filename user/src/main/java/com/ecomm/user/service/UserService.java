package com.ecomm.user.service;

import com.ecomm.user.dto.UserResponse;
import com.ecomm.user.Entity.User;
import com.ecomm.user.dto.UserRequest;

import java.util.List;

public interface UserService {
    List<UserResponse> fetchAllUser();
    void createUser(UserRequest userRequest);
    UserResponse getUserById(Long id);
    UserResponse updateUserById(Long id, UserRequest user);
    void deleteUserById(Long id);
}
