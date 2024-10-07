package com.shopswift.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopswift.ecom.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	public Optional<UserAccount> findOneByUserNameAndPassword(String userName, String password);

	public Optional<UserAccount> findOneByUserName(String userName);

}
