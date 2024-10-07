package com.shopswift.ecom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopswift.ecom.model.AddToCartRequest;
import com.shopswift.ecom.model.CartResponse;
import com.shopswift.ecom.model.OrderResponse;
import com.shopswift.ecom.model.Product;
import com.shopswift.ecom.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public List<Product> fetchProducts() {
		return productService.fetchProducts();
	}

	@GetMapping("/product/{id}")
	public Product fetchProductById(@PathVariable("id") int id) {
		return productService.fetchProductById(id);
	}

	@PostMapping("/addToCart")
	public void addToCart(@RequestBody AddToCartRequest request) throws Exception {
		productService.addToCart(request);
	}

	@GetMapping("/getCart/{userName}")
	public List<CartResponse> getCart(@PathVariable("userName") String userName) {
		return productService.getCart(userName);
	}

	@GetMapping("/getCartCount/{userName}")
	public int getCartCount(@PathVariable("userName") String userName) {
		return productService.getCartCount(userName);
	}

	@GetMapping("/getCartProductCount/{productId}/{userName}")
	public int getCartProductCount(@PathVariable("productId") int productId,
			@PathVariable("userName") String userName) {
		return productService.getCartProductCount(productId, userName);
	}

	@GetMapping("/removeFromCart/{productId}/{userName}")
	public void removeFromCart(@PathVariable("productId") int productId, @PathVariable("userName") String userName) {
		productService.removeFromCart(productId, userName);
	}

	@GetMapping("/decreaseFromCart/{productId}/{userName}")
	public void decreaseFromCart(@PathVariable("productId") int productId, @PathVariable("userName") String userName) {
		productService.decreaseFromCart(productId, userName);
	}

	@GetMapping("/placeOrder/{userName}")
	public ResponseEntity<?> placeOrder(@PathVariable("userName") String userName) {
		return productService.placeOrder(userName);
	}

	@GetMapping("/getOrders/{userName}")
	public List<OrderResponse> getOrders(@PathVariable("userName") String userName) throws Exception {
		return productService.getOrders(userName);
	}

	@GetMapping("/getProductNamesAndId")
	public Map<Integer, String> getProductNamesAndId() throws Exception {
		return productService.getProductNamesAndId();
	}
}
