package com.hungernet2.service.impl;

import com.hungernet2.converter.AbstractConverter;
import com.hungernet2.converter.UserConverter;
import com.hungernet2.model.entity.JwtRequest;
import com.hungernet2.model.entity.Role;
import com.hungernet2.exception.NoCredentialsException;
import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.model.entity.User;
import com.hungernet2.repository.UserRepository;
import com.hungernet2.service.RoleService;
import com.hungernet2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleService roleService;
	private final AbstractConverter<User, UserDTO> userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public UserServiceImpl(final UserRepository userRepository, final RoleService roleService) {
		this.roleService = roleService;
		this.userRepository = userRepository;
		this.userConverter = new UserConverter();
	}

	@Override
	public List<UserDTO> retrieveAllUsers() {
		List<UserDTO> result =
				userRepository.findAll()
						.stream()
						.map(userConverter::toDTO)
						.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<UserDTO> retrieveAllUsersPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<UserDTO> result = new ArrayList<>();
		List<User> temp = userRepository.findAll();
		temp.sort(Comparator.comparing(User::getUsername));

		for (int i = mult - pageSize; i < mult && i < temp.size(); i++) {
			result.add(userConverter.toDTO(temp.get(i)));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<UserDTO> retrieveAllUsersButAdmin() {
		List<UserDTO> temp = retrieveAllUsers();
		List<UserDTO> result = new ArrayList<>();
		for(UserDTO userDTO : temp) {
			for(Role role : userDTO.getRoles()) {
				if(!role.getRoleName().equals("ADMIN")) {
					result.add(userDTO);
				}
			}
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<UserDTO> retrieveAllUsersButAdminPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<UserDTO> result = new ArrayList<>();
		List<UserDTO> temp = retrieveAllUsersButAdmin();
		temp.sort(Comparator.comparing(UserDTO::getId));

		for (int i = mult - pageSize; i < mult && i < temp.size(); i++) {
			result.add(temp.get(i));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}


	@Override
	public UserDTO retrieveByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty()) {
			throw new NoSuchElementException();
		}
		return userConverter.toDTO(user.get());
	}

	@Override
	public User registerNewUser(User user) {
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userRepository.save(user);
	}

	@Override
	public User registerNewUser2(JwtRequest jwtRequest) {
		User user = new User(jwtRequest.getUsername(), jwtRequest.getUserPassword());
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userRepository.save(user);
	}

	@Override
	public UserDTO registerNewUserDTO(JwtRequest jwtRequest) {
		User user = new User(jwtRequest.getUsername(), jwtRequest.getUserPassword());
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userConverter.toDTO(userRepository.save(user));
	}
	private String getEncodedPassword(String password) {
		return  passwordEncoder.encode(password);
	}

	@Override
	public List<UserDTO> retrieveByRoleEnum(String roleEnum) {
		Role role = roleService.retrieveByRole(roleEnum);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		List<UserDTO> result = userRepository.findByRolesIn(roles)
				.stream().map(userConverter::toDTO)
				.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public UserDTO assignRoleToUser(String roleName, Integer id) {
		User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
		for(Role role : user.getRoles()) {
			//can't change role of another admin
			if(!role.getRoleName().equals("ADMIN")) {
				continue;
			} else {
				throw new NoCredentialsException();
			}
		}
		user.getRoles().add(roleService.retrieveByRole(roleName));
		storeUser(user);
		return userConverter.toDTO(user);

	}

	@Override
	public UserDTO storeUser(User user) {
		return userConverter.toDTO(userRepository.save(user));
	}

	@Override
	public void deleteUser(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Override
	public void clearDatabase() {
		userRepository.deleteAll();
	}
}
