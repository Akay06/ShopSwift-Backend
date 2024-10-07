package com.shopswift.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopswift.ecom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findOneByUserName(String userName);
}
