package com.shopswift.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopswift.ecom.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {


	@Query(value = "SELECT MAX(invoice_no) FROM kxr0701_shopswift.e_order", nativeQuery = true)
	//@Query(value = "SELECT MAX(invoice_no) FROM shopswift.e_order", nativeQuery = true)
	public Integer findLastInvoice();

	public List<Order> findByUserId(int userId);
}