package com.jwtwithrolesspringsecurity.service;

import com.jwtwithrolesspringsecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jwtwithrolesspringsecurity.entity.Role;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role createNewRole(Role role) {
		return roleRepository.save(role);
	}
}
