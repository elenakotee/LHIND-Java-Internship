package com.hungernet2.service;

import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.repository.RoleRepository;
import com.hungernet2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hungernet2.model.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	void deleteUser(Integer id);

	void clearDatabase();
}
