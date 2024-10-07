package com.shopswift.ecom.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.shopswift.ecom.model.AddToCartRequest;
import com.shopswift.ecom.model.CartResponse;
import com.shopswift.ecom.model.OrderResponse;
import com.shopswift.ecom.model.Product;

public interface ProductService {

	public List<Product> fetchProducts();

	public Product fetchProductById(int id);

	public void addToCart(AddToCartRequest request) throws Exception;

	public List<CartResponse> getCart(String userName);

	public int getCartCount(String userName);

	public void removeFromCart(int productId, String userName);

	public void decreaseFromCart(int productId, String userName);

	public int getCartProductCount(int productId, String userName);

	public ResponseEntity<?> placeOrder(String userName);

	public List<OrderResponse> getOrders(String userName) throws Exception;

	public Map<Integer, String> getProductNamesAndId() throws Exception;
}
