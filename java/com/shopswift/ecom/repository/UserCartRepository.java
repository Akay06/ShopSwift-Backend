package com.shopswift.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopswift.ecom.model.UserCart;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Integer> {
	List<UserCart> findByUserUserName(String userName);

	long deleteByProductIdAndUserUserName(int productId, String userName);

	Optional<UserCart> findFirstByProductIdAndUserUserName(int productId, String userName);

	List<UserCart> findByProductIdAndUserUserName(int productId, String userName);
}
