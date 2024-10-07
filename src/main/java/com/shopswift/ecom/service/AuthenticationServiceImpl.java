package com.shopswift.ecom.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopswift.ecom.model.AuthenticationRequest;
import com.shopswift.ecom.model.SignupRequest;
import com.shopswift.ecom.model.User;
import com.shopswift.ecom.model.UserAccount;
import com.shopswift.ecom.repository.UserAccountRepository;
import com.shopswift.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> login(AuthenticationRequest request) {

		Optional<UserAccount> userAccount = userAccountRepository.findOneByUserNameAndPassword(request.getUserName(),
				request.getPassword());

		if (userAccount.isPresent()) {
			return ResponseEntity.ok(null);
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<?> signup(SignupRequest request) {
		if (request.getUserName() != null && request.getEmail() != null && request.getMobile() != null
				&& request.getPassword() != null) {
			if (!validateEmail(request.getEmail()) || !validateMobile(request.getMobile())) {
				return ResponseEntity.badRequest().build();
			}

			if (userAccountRepository.findOneByUserName(request.getUserName()).isPresent()) {
				return ResponseEntity.ok("Username taken! Please choose another");
			}

			User user = new User(request.getUserName(), request.getEmail(), request.getMobile());
			user = userRepository.save(user);

			UserAccount userAccount = new UserAccount(request.getUserName(), request.getPassword(), user);
			userAccountRepository.save(userAccount);

			return ResponseEntity.ok(null);
		}

		return ResponseEntity.badRequest().build();

	}

	@Override
	public boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
		return pattern.matcher(email).matches();
	}

	@Override
	public boolean validateMobile(String mobile) {
		Pattern pattern = Pattern.compile("^[0-9]{10}$");
		return pattern.matcher(mobile).matches();
	}

}
