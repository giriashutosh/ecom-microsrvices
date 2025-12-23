package com.ecomm.order.service;

import com.ecomm.order.Entity.CartItem;
//import com.ecomm.order.Entity.Product;
//import com.ecomm.order.Entity.User;
import com.ecomm.order.Entity.UserCart;
import com.ecomm.order.repositiry.CartItemRepositiry;
//import com.ecomm.order.repositiry.ProductRepositiry;
import com.ecomm.order.repositiry.UserCartRepositiry;
//import com.ecomm.order.repositiry.UserRepositiry;
import com.ecomm.order.repositiry.UserCartRepositiry;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    private CartItemRepositiry cartItemRepositiry;
    private UserCartRepositiry userCartRepositiry;

    public CartServiceImpl(CartItemRepositiry cartItemRepositiry,
                           UserCartRepositiry userCartRepositiry){
        this.cartItemRepositiry = cartItemRepositiry;
        this.userCartRepositiry = userCartRepositiry;
       }

    @Override
    @Transactional
    public UserCart addToCart(Long userId, Long productId, int quantity) {
        //Get the user
//        User user= userRepositiry.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
//
//        //Get the product
//        Product product = productRepositiry.findById(productId).orElseThrow(() -> new RuntimeException(("product not found")));

        //Get or create usercart
        UserCart cart = userCartRepositiry.findByUserId(userId).orElseGet(() -> {
            UserCart userCart = new UserCart();
            userCart.setUserId(userId);
            return userCartRepositiry.save(userCart);
        });

        //check the product already exist or not
        Optional<CartItem> existingOptItem = cartItemRepositiry.findByUserCartAndProductId(cart,productId);
        CartItem cartItem;
        if(existingOptItem.isPresent()){
            cartItem = existingOptItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else{
            cartItem = new CartItem();
            cartItem.setUserCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setProductId(productId);
            //cartItem.setPrice(productId);
            cart.getItems().add(cartItem);
        }

        cartItemRepositiry.save(cartItem);
        //userCartRepositiry.save(cart);

        return userCartRepositiry.findById(cart.getId()).get();
    }

    @Override
    public UserCart viewCart(Long userId) {
        //User user = userRepositiry.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
        UserCart cart = userCartRepositiry.findByUserId(userId).orElseThrow(() -> new RuntimeException("cart not found for this user"));
        return cart;
    }

    @Override
    public void removeItemFromCart(Long userId, Long productId) {
//        User user = userRepositiry.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        UserCart cart = userCartRepositiry.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

//        Product product = productRepositiry.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartItemRepositiry.findByUserCartAndProductId(cart, productId)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));
        cart.getItems().remove(item);
        cartItemRepositiry.delete(item);
    }
}
