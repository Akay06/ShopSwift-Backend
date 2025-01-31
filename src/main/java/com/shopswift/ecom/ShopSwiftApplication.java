package com.shopswift.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopSwiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopSwiftApplication.class, args);
	}

}
