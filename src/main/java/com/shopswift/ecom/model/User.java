package com.shopswift.ecom.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String userName;

	@Column
	private String mobile;

	@Column
	private String email;

	@Column
	private String name;

	@Column
	private String address;

	public User(String userName, String email, String mobile) {
		this.userName = userName;
		this.name = userName;
		this.mobile = mobile;
		this.email = email;
		this.address = "";
	}

	public User() {

	}

}
