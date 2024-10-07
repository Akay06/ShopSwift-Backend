package com.shopswift.ecom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopswift.ecom.model.AddToCartRequest;
import com.shopswift.ecom.model.CartResponse;
import com.shopswift.ecom.model.Order;
import com.shopswift.ecom.model.OrderProduct;
import com.shopswift.ecom.model.OrderResponse;
import com.shopswift.ecom.model.Product;
import com.shopswift.ecom.model.User;
import com.shopswift.ecom.model.UserCart;
import com.shopswift.ecom.repository.OrderRepository;
import com.shopswift.ecom.repository.ProductRepository;
import com.shopswift.ecom.repository.UserCartRepository;
import com.shopswift.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserCartRepository userCartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Product> fetchProducts() {

		return productRepository.findAll();
	}

	@Override
	public Product fetchProductById(int id) {

		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent())
			return product.get();

		return null;
	}

	@Override
	public void addToCart(AddToCartRequest request) throws Exception {

		Optional<Product> product = productRepository.findById(request.getProductId());

		if (!product.isPresent())
			throw new Exception("Invalid Product Id");

		Optional<User> user = userRepository.findOneByUserName(request.getUserName());

		if (!user.isPresent())
			throw new Exception("Invalid user name");

		if (request.getCount() < 1)
			throw new Exception("Count should be greater than 0");

		userCartRepository.save(new UserCart(user.get(), product.get(), request.getCount()));
	}

	@Override
	public List<CartResponse> getCart(String userName) {

		List<UserCart> userCart = userCartRepository.findByUserUserName(userName);

		Map<Integer, CartResponse> result = new HashMap<>();

		for (UserCart cart : userCart) {
			if (result.containsKey(cart.getProduct().getId())) {
				CartResponse temp = result.get(cart.getProduct().getId());
				temp.setCount(temp.getCount() + cart.getProductCount());
			} else {
				CartResponse temp = new CartResponse();

				temp.setProductId(cart.getProduct().getId());
				temp.setProductName(cart.getProduct().getProductName());
				temp.setPreviewUrl(cart.getProduct().getPreviewUrl());
				temp.setPrice(cart.getProduct().getPrice());
				temp.setCount(cart.getProductCount());
				temp.setStockCount(cart.getProduct().getStockCount());

				result.put(cart.getProduct().getId(), temp);
			}
		}

		return new ArrayList<>(result.values());
	}

	@Override
	public int getCartCount(String userName) {

		List<UserCart> userCart = userCartRepository.findByUserUserName(userName);

		int count = 0;

		for (UserCart cart : userCart) {
			count += cart.getProductCount();
		}

		return count;
	}

	@Override
	public void removeFromCart(int productId, String userName) {

		userCartRepository.deleteByProductIdAndUserUserName(productId, userName);

	}

	@Override
	public void decreaseFromCart(int productId, String userName) {

		Optional<UserCart> userCart = userCartRepository.findFirstByProductIdAndUserUserName(productId, userName);

		if (userCart.isPresent()) {
			int count = userCart.get().getProductCount();
			if (count > 1) {
				userCart.get().setProductCount(count - 1);
			} else {
				userCartRepository.deleteById(userCart.get().getId());
			}
		}

	}

	@Override
	public int getCartProductCount(int productId, String userName) {
		List<UserCart> userCart = userCartRepository.findByProductIdAndUserUserName(productId, userName);

		int count = 0;

		for (UserCart cart : userCart) {
			count += cart.getProductCount();
		}

		return count;
	}

	@Override
	public ResponseEntity<?> placeOrder(String userName) {

		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent())
			ResponseEntity.notFound().build();

		List<CartResponse> userCartList = getCart(userName);

		Integer invoiceNo = orderRepository.findLastInvoice();

		if (invoiceNo == null || invoiceNo == 0)
			invoiceNo = 1002160170;
		else
			invoiceNo += 1;

		for (CartResponse userCart : userCartList) {
			Order order = new Order();
			order.setInvoiceNo(invoiceNo.intValue());

			Optional<Product> product = productRepository.findById(userCart.getProductId());
			if (!product.isPresent())
				return ResponseEntity.notFound().build();

			order.setProduct(product.get());
			order.setQuantity(userCart.getCount());
			order.setUser(user.get());
			order.setOrderedAt(LocalDateTime.now());

			orderRepository.save(order);

			product.get().setStockCount(product.get().getStockCount() - userCart.getCount());

			productRepository.save(product.get());

		}

		userCartRepository.deleteAll(userCartRepository.findByUserUserName(userName));

		return ResponseEntity.ok(null);
	}

	@Override
	public List<OrderResponse> getOrders(String userName) throws Exception {

		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent())
			throw new Exception("Invalid user name");

		List<Order> orders = orderRepository.findByUserId(user.get().getId());

		Map<Integer, OrderResponse> result = new HashMap<>();

		for (Order order : orders) {
			if (result.containsKey(order.getInvoiceNo())) {

				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setProductName(order.getProduct().getProductName());
				orderProduct.setProductPrice(order.getProduct().getPrice());
				orderProduct.setProductQty(order.getQuantity());

				result.get(order.getInvoiceNo()).getOrderProducts().add(orderProduct);

				result.get(order.getInvoiceNo()).setTotal(result.get(order.getInvoiceNo()).getTotal()
						+ orderProduct.getProductPrice() * orderProduct.getProductQty());

			} else {
				OrderResponse orderResponse = new OrderResponse();
				orderResponse.setOrderedAt(order.getOrderedAt() + "");
				orderResponse.setOrderNo(order.getInvoiceNo());
				orderResponse.setTotal(order.getProduct().getPrice() * order.getQuantity());

				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setProductName(order.getProduct().getProductName());
				orderProduct.setProductPrice(order.getProduct().getPrice());
				orderProduct.setProductQty(order.getQuantity());

				List<OrderProduct> orderProductList = new ArrayList<>();
				orderProductList.add(orderProduct);

				orderResponse.setOrderProducts(orderProductList);

				result.put(order.getInvoiceNo(), orderResponse);
			}
		}

		TreeMap<Integer, OrderResponse> sortedMap = new TreeMap<>(Collections.reverseOrder());
		sortedMap.putAll(result);
		return new ArrayList<>(sortedMap.values());
	}

	@Override
	public Map<Integer, String> getProductNamesAndId() throws Exception {

		List<Product> products = productRepository.findAll();

		if (products.isEmpty())
			throw new Exception("No product exists");

		Map<Integer, String> result = new HashMap<>();

		for (Product product : products) {
			result.put(product.getId(), product.getProductName());
		}

		return result;

	}

}
