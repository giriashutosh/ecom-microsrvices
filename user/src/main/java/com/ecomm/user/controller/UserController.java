package com.ecomm.user.controller;

import com.ecomm.user.Entity.User;
import com.ecomm.user.dto.UserRequest;
import com.ecomm.user.dto.UserResponse;
import com.ecomm.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userList = userService.fetchAllUser();
        return ResponseEntity.ok(userList);
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        return ResponseEntity.ok("User has been created successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest){
        UserResponse user = userService.updateUserById(id, updatedUserRequest);
        if (user == null) {
            return ResponseEntity.notFound().build(); // 404 if user doesn't exist
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User has been deleted successfully!");
    }

}
