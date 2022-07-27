package com.hungernet2.model.dto;

import com.hungernet2.model.entity.*;

import java.util.Set;

public class UserDTO {

	private Integer id;

	private String username;

	private Set<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", role=" + roles +
				'}';
	}
}