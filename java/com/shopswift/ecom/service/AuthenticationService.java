package com.shopswift.ecom.service;

import org.springframework.http.ResponseEntity;

import com.shopswift.ecom.model.AuthenticationRequest;
import com.shopswift.ecom.model.SignupRequest;

public interface AuthenticationService {

	public ResponseEntity<?> login(AuthenticationRequest request);

	public ResponseEntity<?> signup(SignupRequest request);

	public boolean validateEmail(String email);

	public boolean validateMobile(String mobile);
}
