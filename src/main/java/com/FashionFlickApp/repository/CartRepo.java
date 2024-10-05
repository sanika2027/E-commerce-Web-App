package com.FashionFlickApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FashionFlickApp.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
	Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
    List<Cart> findByUserId(Long userId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserId(Long userId);
    Cart findByUserIdAndProductIdAndSize(Long userId, Long productId, String size);
}
