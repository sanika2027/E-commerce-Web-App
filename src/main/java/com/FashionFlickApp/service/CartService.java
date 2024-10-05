package com.FashionFlickApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FashionFlickApp.exception.ResourceNotFoundException;
import com.FashionFlickApp.model.Cart;
import com.FashionFlickApp.model.Product;
import com.FashionFlickApp.repository.CartRepo;
import com.FashionFlickApp.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private ProductRepo productRepository; // Assuming you have a product repository
    
    public void addOrUpdateCart(Long userId, Long productId, int quantity, String size) {
        Cart existingItem = cartRepository.findByUserIdAndProductIdAndSize(userId, productId, size);
        if (existingItem != null) {
            // Update the quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartRepository.save(existingItem);
        } else {
            // Create a new item
            Cart newItem = new Cart();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setSize(size);
            cartRepository.save(newItem);
        }
    }

    public void addToCart(Long userId, Long productId, int quantity, String size) {
        // Check if product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Find existing cart item
        Optional<Cart> cartOptional = cartRepository.findByUserIdAndProductId(userId, productId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setQuantity(quantity);  // Update quantity
            cartRepository.save(cart);
        } else {
            Cart newCart = new Cart(userId, productId, quantity, size);
            cartRepository.save(newCart);
        }
    }

    public List<Cart> viewCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void updateCart(Long userId, Long productId, int quantity, String size) {
        Optional<Cart> cartOptional = cartRepository.findByUserIdAndProductId(userId, productId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setQuantity(quantity); 
            cart.setSize(size);// Update quantity
            cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("Cart item not found");
        }
    }

    @Transactional
    public void removeFromCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
