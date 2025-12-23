package com.ecomm.order.controller;

import com.ecomm.order.Entity.UserCart;
import com.ecomm.order.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity<UserCart> addToCart(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") int quantity){

     UserCart userCart = cartService.addToCart(userId, productId, quantity);
     return new ResponseEntity<>(userCart,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserCart> viewCart(@PathVariable Long userId){
        UserCart cart = cartService.viewCart(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public String removeItemFromCart(@PathVariable Long userId,
                                     @PathVariable Long productId){
        cartService.removeItemFromCart(userId, productId);
        return "Item removed successfully!!";
    }
}
