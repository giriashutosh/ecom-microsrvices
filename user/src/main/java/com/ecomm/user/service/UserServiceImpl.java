package com.ecomm.user.service;

import com.ecomm.user.Entity.User;
import com.ecomm.user.dto.UserRequest;
import com.ecomm.user.dto.UserResponse;
import com.ecomm.user.repositiry.UserRepositiry;
import com.ecomm.user.Entity.Address;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepositiry userRepositiry;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserResponse> fetchAllUser() {
        return userRepositiry.findAll()
                .stream()
                .map(this:: mapUserToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserRequest userRequest) {
       // user.setId(++id);
        User user = new User();
        user = mapUserRequestToUser(user, userRequest);
        userRepositiry.save(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepositiry.findById(id)
                .map(this::mapUserToUserResponse)
                .orElse(null);
    }

    @Override
    public UserResponse updateUserById(Long id, UserRequest updatedUserRequest) {
        return userRepositiry.findById(id).map(
                user -> {
                    User updatedUser = mapUserRequestToUser(user, updatedUserRequest);
                    userRepositiry.save(updatedUser);
                    return mapUserToUserResponse(updatedUser);
                }
                ).orElse(null);
    }

    @Override
    public void deleteUserById(Long id) {
           userRepositiry.deleteById(id);
    }

    public User mapUserRequestToUser(User user, UserRequest userRequest){
        //user.setId(userRequest.getId());
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone_no(userRequest.getPhone_no());
        if(userRequest.getAddress() != null){
            Address address = new Address();
            //address.setId(userRequest.getAddress().getId());
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);
        }
        return user;
    }

    public UserResponse mapUserToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone_no(user.getPhone_no());
        response.setAge(user.getAge());
        if(user.getAddress()!=null){
            Address address = new Address();
            address.setId(user.getAddress().getId());
            address.setStreet(user.getAddress().getStreet());
            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
            address.setZipcode(user.getAddress().getZipcode());
            address.setCountry(user.getAddress().getCountry());

            response.setAddress(address);
        }
        return response;
    }
}
