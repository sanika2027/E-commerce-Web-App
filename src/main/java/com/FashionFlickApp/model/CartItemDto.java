package com.FashionFlickApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String size;
}
