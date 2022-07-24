package com.jwtwithrolesspringsecurity.service;

import com.jwtwithrolesspringsecurity.repository.RoleRepository;
import com.jwtwithrolesspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jwtwithrolesspringsecurity.entity.User;
import com.jwtwithrolesspringsecurity.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUser(User user) {
		Role role = roleRepository.findById("User").get();

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);

		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return userRepository.save(user);
	}

	public void initRoleAndUser() {

		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleRepository.save(userRole);

		User adminUser = new User();
		adminUser.setUsername("admin123");
		adminUser.setUserPassword(getEncodedPassword("123"));
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userRepository.save(adminUser);

		/*User user = new User();
		user.setUsername("user123");
		user.setUserPassword(getEncodedPassword("123"));
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		userRepository.save(user);*/

	}

	public String getEncodedPassword(String password) {
		return  passwordEncoder.encode(password);
	}

}
