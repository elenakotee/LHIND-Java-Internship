package com.hungernet2.model.builder;

import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.model.entity.*;

import java.util.Set;

public class UserDTOBuilder {

	private UserDTO userDTO = new UserDTO();

	public UserDTO build() { return userDTO;}

	public UserDTOBuilder withId(Integer id) {
		userDTO.setId(id);
		return this;
	}

	public UserDTOBuilder withUsername(String username) {
		userDTO.setUsername(username);
		return this;
	}

	public UserDTOBuilder withRoles(Set<Role> roles) {
		userDTO.setRoles(roles);
		return this;
	}
}
