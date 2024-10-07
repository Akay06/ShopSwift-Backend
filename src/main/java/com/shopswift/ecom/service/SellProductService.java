package com.shopswift.ecom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.shopswift.ecom.model.SellProductRequest;
import com.shopswift.ecom.model.SellProductResponse;

public interface SellProductService {

	ResponseEntity<?> sellProduct(SellProductRequest request);

	List<SellProductResponse> getProducts(String userName) throws Exception;

	ResponseEntity<?> sellEditProduct(SellProductRequest request);

	ResponseEntity<?> deleteProduct(int id, String userName);

}
