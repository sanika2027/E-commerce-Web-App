package com.FashionFlickApp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDto {
	private Long productId;
	private int quantity;
}
