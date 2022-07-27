package com.hungernet2.controller;

import com.hungernet2.model.entity.JwtRequest;
import com.hungernet2.model.entity.JwtResponse;
import com.hungernet2.model.entity.*;
import com.hungernet2.model.requestentities.RequestUser;
import com.hungernet2.service.JwtService;
import com.hungernet2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@PostMapping({"/authenticate"})
	public ResponseEntity<String> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		jwtService.createJwtToken(jwtRequest);
		return new ResponseEntity("Authenticated successfully", HttpStatus.OK);
	}

	@PostMapping({"/registerNewUser"})
	public ResponseEntity<String> registerNewUser(@RequestBody User user) {
		userService.registerNewUser(user);
		return new ResponseEntity("Registered successfully", HttpStatus.OK);
	}
}
