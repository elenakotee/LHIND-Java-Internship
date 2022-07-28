package com.hungernet2.controller;

import com.hungernet2.model.entity.JwtRequest;
import com.hungernet2.model.entity.JwtResponse;
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
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}

	@PostMapping({"/registerNewUser"})
	public ResponseEntity<String> registerNewUser(@RequestBody JwtRequest jwtRequest) {
		userService.registerNewUser2(jwtRequest);
		return new ResponseEntity<>("Registered successfully", HttpStatus.OK);
	}
}
