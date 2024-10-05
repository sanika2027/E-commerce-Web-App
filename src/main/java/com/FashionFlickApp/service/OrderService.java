package com.FashionFlickApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FashionFlickApp.exception.ResourceNotFoundException;
import com.FashionFlickApp.model.Order;
import com.FashionFlickApp.model.OrderItem;
import com.FashionFlickApp.model.OrderItemDto;
import com.FashionFlickApp.model.Product;
import com.FashionFlickApp.repository.CartRepo;
import com.FashionFlickApp.repository.OrderRepo;
import com.FashionFlickApp.repository.ProductRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	CartRepo cartRepo;

	@Autowired
	ProductRepo productRepo;

	public Order createOrder(Long userId, List<OrderItemDto> orderItemsDto) {
		// Convert DTO to entity
		List<OrderItem> orderItems = orderItemsDto.stream().map(item -> {
			Product product = productRepo.findById(item.getProductId())
					.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
			return new OrderItem(product.getId(), item.getQuantity(), product.getPrice());
		}).collect(Collectors.toList());

		Order order = new Order(userId, orderItems);
		return orderRepo.save(order);
	}

	public List<Order> viewOrders(Long userId) {
		return orderRepo.findByUserId(userId);
	}

	public Order updateOrderStatus(Long orderId, String status) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		order.setStatus(status);
		return orderRepo.save(order);
	}

	public void cancelOrder(Long orderId) {
		orderRepo.deleteById(orderId);
	}
}
