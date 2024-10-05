package com.FashionFlickApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistItemDto {
	private Long userId;
    private Long productId;
}
