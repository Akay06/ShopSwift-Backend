package com.shopswift.ecom.model;

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
@Table(name = "user_account")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccount {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String userName;

	@Column
	private String password;

	@OneToOne
	private User user;

	public UserAccount(String userName, String password, User user) {
		this.userName = userName;
		this.password = password;
		this.user = user;
	}

	public UserAccount() {

	}
}
