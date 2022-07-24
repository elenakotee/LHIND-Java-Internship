package com.jwtwithrolesspringsecurity.entity;

import javax.persistence.*;

import com.jwtwithrolesspringsecurity.entity.Role;

import java.util.Set;

@Entity
public class User {

	@Id
	private String username;
	private String userPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", //creates a third table automatically: user_role
			joinColumns = {
			@JoinColumn(name = "USER_ID") //contains user id and its associated role id
			},
			inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID")
			}
	)
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}


