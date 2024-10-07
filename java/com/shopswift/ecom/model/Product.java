package com.shopswift.ecom.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String productName;

	@Column
	private String previewUrl;

	@Column
	private String description;

	@Column
	private String brand;

	@Column
	private double price;

	@Column
	private int stockCount;

	@JsonManagedReference
	@OneToMany(mappedBy = "product")
	private List<Image> image;

}
