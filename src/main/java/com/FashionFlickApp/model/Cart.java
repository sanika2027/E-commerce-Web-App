package com.FashionFlickApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Long userId;
	Long productId;
	Integer quantity;
	String size;
	
	public Cart(Long userId, Long productId, Integer quantity, String size) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.size = size;
	}
}
