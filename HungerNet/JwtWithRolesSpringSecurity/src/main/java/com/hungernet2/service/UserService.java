package com.hungernet2.service;

import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.model.entity.*;

import java.util.List;

public interface UserService {

	List<UserDTO> retrieveAllUsers();

	List<UserDTO> retrieveAllUsersPagination(Integer pageNumber, Integer pageSize);

	List<UserDTO> retrieveAllUsersButAdmin();

	List<UserDTO> retrieveAllUsersButAdminPagination(Integer pageNumber, Integer pageSize);

	UserDTO retrieveByUsername(String username);

	List<UserDTO> retrieveByRoleEnum(String roleEnum);

	UserDTO assignRoleToUser(String roleName, Integer id);

	UserDTO storeUser(User user);

	User registerNewUser(User user);

	User registerNewUser2(JwtRequest jwtRequest);

	UserDTO registerNewUserDTO(JwtRequest jwtRequest);
	void deleteUser(Integer id);

	void clearDatabase();
}
