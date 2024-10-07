package com.shopswift.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopswift.ecom.model.SellProduct;

@Repository
public interface SellProductRepository extends JpaRepository<SellProduct, Integer> {

	public List<SellProduct> findByUserUserNameOrderByPlacedAtDesc(String userName);
}
