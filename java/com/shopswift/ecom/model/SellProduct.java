package com.shopswift.ecom.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sell_product")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SellProduct {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String productName;

	@Column
	private double price;

	@Column
	private String description;

	@Column
	private String address;

	@Column
	private String mobile;

	@OneToOne
	private User user;

	@Column
	private LocalDateTime placedAt;
}
