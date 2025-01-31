package com.shopswift.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopswift.ecom.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

	Optional<AppUser> findOneByUserName(String userName);
}
