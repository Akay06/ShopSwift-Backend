package com.shopswift.ecom.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartResponse {
	int productId;
	String productName;
	String previewUrl;
	int count;
	double price;
	int stockCount;
}
