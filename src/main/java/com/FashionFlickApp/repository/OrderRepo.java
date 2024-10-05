package com.FashionFlickApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FashionFlickApp.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);
}
