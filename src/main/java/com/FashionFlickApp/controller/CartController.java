package com.FashionFlickApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FashionFlickApp.model.Cart;
import com.FashionFlickApp.model.CartItemDto;
import com.FashionFlickApp.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add product to cart
    /*@PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody CartItemDto cartItemDto) {
        cartService.addToCart(cartItemDto.getUserId(), cartItemDto.getProductId(), cartItemDto.getQuantity(), cartItemDto.getSize());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product added to cart successfully.");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        //return ResponseEntity.status(HttpStatus.CREATED).body("Product added to cart successfully.");
    }*/
    
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItemDto cartItemDto) {
        cartService.addOrUpdateCart(cartItemDto.getUserId(), cartItemDto.getProductId(), cartItemDto.getQuantity(), cartItemDto.getSize());
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"Product added/updated in cart successfully.\"}");
    }
    // View cart
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> viewCart(@PathVariable Long userId) {
        List<Cart> cartItems = cartService.viewCart(userId);
        return ResponseEntity.ok(cartItems);
    }

    // Update cart
    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestBody CartItemDto cartItemDto) {
        cartService.updateCart(cartItemDto.getUserId(), cartItemDto.getProductId(), cartItemDto.getQuantity(), cartItemDto.getSize());
        return ResponseEntity.ok("Cart updated successfully.");
    }

    // Remove product from cart
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartItemDto cartItemDto) {
        cartService.removeFromCart(cartItemDto.getUserId(), cartItemDto.getProductId());
        return ResponseEntity.ok("Product removed from cart.");
    }

    // Clear cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared.");
    }
}
