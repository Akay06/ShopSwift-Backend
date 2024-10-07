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
@Table(name = "e_order")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int invoiceNo;

	@OneToOne
	private User user;

	@OneToOne
	private Product product;

	@Column
	private int quantity;

	@Column
	private LocalDateTime orderedAt;
}
