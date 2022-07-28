package com.hungernet2.model.builder;

import com.hungernet2.model.entity.*;

import java.util.Set;

public class UserBuilder {

	private User user = new User();

	public User build() { return user;}


	public UserBuilder withUsername(String username) {
		user.setUsername(username);
		return this;
	}

	public UserBuilder withRoles(Set<Role> roles) {
		user.setRoles(roles);
		return this;
	}

}