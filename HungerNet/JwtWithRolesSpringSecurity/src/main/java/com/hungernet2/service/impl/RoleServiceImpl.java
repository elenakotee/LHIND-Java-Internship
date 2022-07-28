package com.hungernet2.service.impl;

import com.hungernet2.repository.RoleRepository;
import com.hungernet2.service.RoleService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hungernet2.model.entity.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public RoleServiceImpl(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	@Override
	public List<Role> retrieveAllRoles() {
		List<Role> result = roleRepository.findAll();
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Role retrieveByRole(String roleName) {
		Optional<Role> role = roleRepository.findByRoleName(roleName);
		if(role.isEmpty()) {
			throw new NoSuchElementException();
		}
		return role.get();
	}

	@Override
	public Role storeRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Integer id) {
		if (roleRepository.existsById(id)) {
			roleRepository.deleteById(id);
		} else {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Override
	public void clearDatabase() {
		roleRepository.deleteAll();
	}
}
