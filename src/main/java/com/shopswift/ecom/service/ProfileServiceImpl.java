package com.shopswift.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopswift.ecom.model.ProfileRequest;
import com.shopswift.ecom.model.ProfileResponse;
import com.shopswift.ecom.model.User;
import com.shopswift.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ProfileResponse getProfile(String userName) throws Exception {

		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent()) {
			throw new Exception("User profile not found");
		}

		ProfileResponse response = new ProfileResponse();
		response.setName(user.get().getName());
		response.setAddress(user.get().getAddress());
		response.setEmail(user.get().getEmail());
		response.setPhone(user.get().getMobile());

		return response;
	}

	@Override
	public ResponseEntity<?> editProfile(ProfileRequest request, String userName) {

		Optional<User> user = userRepository.findOneByUserName(userName);

		if (!user.isPresent()) {
			ResponseEntity.notFound().build();
		}

		user.get().setAddress(request.getAddress());
		user.get().setEmail(request.getEmail());
		user.get().setMobile(request.getPhone());
		user.get().setName(request.getName());

		userRepository.save(user.get());

		return ResponseEntity.ok(null);
	}

}
