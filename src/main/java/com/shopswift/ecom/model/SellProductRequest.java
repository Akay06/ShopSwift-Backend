package com.shopswift.ecom.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SellProductRequest {
	int id;
	String productName;
	String address;
	Double price;
	String description;
	String mobile;
	String userName;
}
