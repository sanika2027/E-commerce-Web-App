package com.FashionFlickApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FashionFlickApp.exception.ResourceNotFoundException;
import com.FashionFlickApp.model.Product;
import com.FashionFlickApp.model.Wishlist;
import com.FashionFlickApp.repository.ProductRepo;
import com.FashionFlickApp.repository.WishListRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishListRepo wishlistRepository;

    @Autowired
    private ProductRepo productRepository;

    public void addToWishlist(Long userId, Long productId) {
        // Check if product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Check if already in wishlist
        if (wishlistRepository.findByUserIdAndProductId(userId, productId).isEmpty()) {
            Wishlist wishlistItem = new Wishlist(userId, productId, LocalDateTime.now());
            wishlistRepository.save(wishlistItem);
        }
    }

    public List<Wishlist> viewWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Transactional
    public void removeFromWishlist(Long userId, Long productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }
}

