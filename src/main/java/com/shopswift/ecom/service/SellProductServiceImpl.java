package com.shopswift.ecom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopswift.ecom.model.SellProduct;
import com.shopswift.ecom.model.SellProductRequest;
import com.shopswift.ecom.model.SellProductResponse;
import com.shopswift.ecom.model.User;
import com.shopswift.ecom.repository.SellProductRepository;
import com.shopswift.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SellProductServiceImpl implements SellProductService {

	@Autowired
	private SellProductRepository sellProductRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> sellProduct(SellProductRequest request) {

		Optional<User> user = userRepository.findOneByUserName(request.getUserName());

		if (!user.isPresent())
			return ResponseEntity.notFound().build();

		SellProduct sellProduct = new SellProduct();

		sellProduct.setAddress(request.getAddress());
		sellProduct.setDescription(request.getDescription());
		sellProduct.setMobile(request.getMobile());
		sellProduct.setPrice(request.getPrice());
		sellProduct.setProductName(request.getProductName());
		sellProduct.setUser(user.get());
		sellProduct.setPlacedAt(LocalDateTime.now());

		sellProductRepository.save(sellProduct);

		return ResponseEntity.ok(null);

	}

	@Override
	public List<SellProductResponse> getProducts(String userName) throws Exception {

		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent())
			throw new Exception("Invalid User");

		List<SellProduct> sellProducts = sellProductRepository.findByUserUserNameOrderByPlacedAtDesc(userName);

		List<SellProductResponse> response = new ArrayList<>();

		for (SellProduct sellProduct : sellProducts) {
			SellProductResponse sellProductResponse = new SellProductResponse();

			sellProductResponse.setAddress(sellProduct.getAddress());
			sellProductResponse.setDateTime(sellProduct.getPlacedAt() + "");
			sellProductResponse.setDescription(sellProduct.getDescription());
			sellProductResponse.setMobile(sellProduct.getMobile());
			sellProductResponse.setPrice(sellProduct.getPrice());
			sellProductResponse.setProductName(sellProduct.getProductName());
			sellProductResponse.setId(sellProduct.getId());

			response.add(sellProductResponse);
		}

		return response;
	}

	@Override
	public ResponseEntity<?> sellEditProduct(SellProductRequest request) {

		Optional<User> user = userRepository.findOneByUserName(request.getUserName());

		if (!user.isPresent())
			return ResponseEntity.notFound().build();

		Optional<SellProduct> sellProduct = sellProductRepository.findById(request.getId());

		if (!sellProduct.isPresent())
			return ResponseEntity.notFound().build();

		sellProduct.get().setAddress(request.getAddress());
		sellProduct.get().setDescription(request.getDescription());
		sellProduct.get().setMobile(request.getMobile());
		sellProduct.get().setPrice(request.getPrice());
		sellProduct.get().setProductName(request.getProductName());
		sellProduct.get().setUser(user.get());
		sellProduct.get().setPlacedAt(LocalDateTime.now());

		sellProductRepository.save(sellProduct.get());

		return ResponseEntity.ok(null);

	}

	@Override
	public ResponseEntity<?> deleteProduct(int id, String userName) {
		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent())
			return ResponseEntity.notFound().build();

		Optional<SellProduct> sellProduct = sellProductRepository.findById(id);

		if (!sellProduct.isPresent())
			return ResponseEntity.notFound().build();

		sellProductRepository.deleteById(id);

		return ResponseEntity.ok(null);
	}

}
