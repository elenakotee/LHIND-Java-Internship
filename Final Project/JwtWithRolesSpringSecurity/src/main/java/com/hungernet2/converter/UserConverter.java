package com.hungernet2.converter;

import com.hungernet2.model.builder.UserBuilder;
import com.hungernet2.model.builder.UserDTOBuilder;
import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.model.entity.*;

public class UserConverter extends AbstractConverter<User, UserDTO>{
	@Override
	public User toEntity(UserDTO userDTO) {
		return new UserBuilder()
				.withUsername(userDTO.getUsername())
				.withRoles(userDTO.getRoles())
				.build();
	}

	@Override
	public UserDTO toDTO(User user) {
		return new UserDTOBuilder()
				.withUsername(user.getUsername())
				.withRoles(user.getRoles())
				.build();
	}
}