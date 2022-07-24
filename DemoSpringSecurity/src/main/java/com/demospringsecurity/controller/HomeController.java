package com.demospringsecurity.controller;

import com.demospringsecurity.entity.User;
import com.demospringsecurity.entity.UserModel;
import com.demospringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public User register (@RequestBody UserModel userModel) {
		User newUser =  new User();
		newUser.setEmail(userModel.getEmail());
		newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
		newUser.setAuthority(userModel.getAuthority());
		return userRepository.save(newUser);
	}

	@PostMapping("/login")
	public ResponseEntity<HttpStatus> login(@RequestBody UserModel userModel) {
		Authentication authentication;

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid credential");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String protectedPage () {
		return "displaying the dashboard page contents";
	}

	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String profilePage () {
		return "displaying the profile page contents";
	}
}
