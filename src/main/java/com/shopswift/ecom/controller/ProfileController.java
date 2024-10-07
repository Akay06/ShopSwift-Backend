package com.shopswift.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopswift.ecom.model.ProfileRequest;
import com.shopswift.ecom.model.ProfileResponse;
import com.shopswift.ecom.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile/{userName}")
	public ProfileResponse getProfile(@PathVariable("userName") String userName) throws Exception {
		return profileService.getProfile(userName);
	}

	@PostMapping("/profile/edit/{userName}")
	public ResponseEntity<?> editProfile(@RequestBody ProfileRequest request,
			@PathVariable("userName") String userName) {
		return profileService.editProfile(request, userName);
	}
}
