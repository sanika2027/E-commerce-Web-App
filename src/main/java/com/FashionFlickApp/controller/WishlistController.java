package com.FashionFlickApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FashionFlickApp.model.Wishlist;
import com.FashionFlickApp.model.WishlistItemDto;
import com.FashionFlickApp.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Add a product to the wishlist
    @PostMapping("/add")
    public ResponseEntity<String> addToWishlist(@RequestBody WishlistItemDto wishlistItemDto) {
        wishlistService.addToWishlist(wishlistItemDto.getUserId(), wishlistItemDto.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist.");
    }

    // View wishlist
    @GetMapping("/{userId}")
    public ResponseEntity<List<Wishlist>> viewWishlist(@PathVariable Long userId) {
        List<Wishlist> wishlistItems = wishlistService.viewWishlist(userId);
        return ResponseEntity.ok(wishlistItems);
    }

    // Remove a product from the wishlist
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromWishlist(@RequestBody WishlistItemDto wishlistItemDto) {
        wishlistService.removeFromWishlist(wishlistItemDto.getUserId(), wishlistItemDto.getProductId());
        return ResponseEntity.ok("Product removed from wishlist.");
    }
}
