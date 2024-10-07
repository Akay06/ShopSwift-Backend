package com.shopswift.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopswift.ecom.model.SellProductRequest;
import com.shopswift.ecom.model.SellProductResponse;
import com.shopswift.ecom.service.SellProductService;

@RestController
public class SellController {

	@Autowired
	private SellProductService sellProductService;

	@PostMapping("/sell/product")
	public ResponseEntity<?> sellProduct(@RequestBody SellProductRequest request) {
		return sellProductService.sellProduct(request);
	}

	@GetMapping("/sell/getProducts/{userName}")
	public List<SellProductResponse> getProducts(@PathVariable("userName") String userName) throws Exception {
		return sellProductService.getProducts(userName);
	}

	@PostMapping("/sell/editProduct")
	public ResponseEntity<?> sellEditProduct(@RequestBody SellProductRequest request) {
		return sellProductService.sellEditProduct(request);
	}

	@GetMapping("/sell/deleteProduct/{id}/{userName}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id, @PathVariable("userName") String userName) {
		return sellProductService.deleteProduct(id, userName);
	}

}
