package com.FashionFlickApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FashionFlickApp.model.Order;
import com.FashionFlickApp.model.OrderRequestDto;
import com.FashionFlickApp.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	 @Autowired
	    private OrderService orderService;

	    // Create a new order
	    @PostMapping("/create")
	    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
	        Order newOrder = orderService.createOrder(orderRequestDto.getUserId(), orderRequestDto.getOrderItems());
	        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
	    }

	    // View orders for a user
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Order>> viewOrders(@PathVariable Long userId) {
	        List<Order> orders = orderService.viewOrders(userId);
	        return ResponseEntity.ok(orders);
	    }

	    // Update order status (e.g., for admin or system)
	    @PutMapping("/{orderId}/status")
	    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
	        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
	        return ResponseEntity.ok(updatedOrder);
	    }

	    // Cancel an order
	    @DeleteMapping("/cancel/{orderId}")
	    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
	        orderService.cancelOrder(orderId);
	        return ResponseEntity.ok("Order cancelled successfully.");
	    }
}
