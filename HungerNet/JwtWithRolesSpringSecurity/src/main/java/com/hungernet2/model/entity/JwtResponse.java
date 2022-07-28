package com.hungernet2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {

	@JsonIgnore
	private User user;
	private String jwtToken;

	public JwtResponse(User user, String jwtToken) {
		this.user = user;
		this.jwtToken = jwtToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
