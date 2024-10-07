package com.shopswift.ecom.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {
	int id;
	String name;
	String preview;
	List<String> photos;
	String description;
	String brand;
	double price;
}
