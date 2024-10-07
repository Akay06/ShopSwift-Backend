package com.shopswift.ecom.service;

import org.springframework.http.ResponseEntity;

import com.shopswift.ecom.model.ProfileRequest;
import com.shopswift.ecom.model.ProfileResponse;

public interface ProfileService {

	public ProfileResponse getProfile(String userName) throws Exception;

	public ResponseEntity<?> editProfile(ProfileRequest request, String userName);

}
