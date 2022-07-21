package com.springbootrest.service.impl;


import com.springbootrest.model.enums.RoleEnum;
import com.springbootrest.repository.RoleRepository;
import com.springbootrest.service.RoleService;
import org.springframework.stereotype.Component;
import com.springbootrest.model.entity.Role;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> retrieveAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> retrieveRolesByRole(RoleEnum role) {
		return roleRepository.findByRole(role);
	}

	@Override
	public List<Role> retrieveRolesByDescription(String description) {
		return roleRepository.findByDescription(description);
	}

	@Override
	public Role storeRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Boolean deleteRole(Integer id) {
		roleRepository.deleteById(id);
		return true;
	}

	@Override
	public Boolean clearDatabase() {
		roleRepository.deleteAll();
		return true;
	}
}
