package com.springbootrest.controller;


import com.springbootrest.model.enums.RoleEnum;
import com.springbootrest.service.RoleService;
import org.springframework.web.bind.annotation.*;
import com.springbootrest.model.entity.Role;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public List<Role> findAll() {
		return roleService.retrieveAllRoles();
	}

	@GetMapping("/{role}")
	public List<Role> findByRole(@PathVariable("role") RoleEnum role) {
		return roleService.retrieveRolesByRole(role);
	}

	@GetMapping("/{description}")
	public List<Role> findByDescription(@PathVariable("description") String description) {
		return roleService.retrieveRolesByDescription(description);
	}

	@PostMapping
	public Role storeRole(@RequestBody Role role) {
		return roleService.storeRole(role);
	}

	@PutMapping
	public Role updateRole(@RequestBody Role role) {
		return roleService.storeRole(role);
	}

	@DeleteMapping
	public Boolean deleteRole(@RequestParam(name = "id") Integer id) {
		return roleService.deleteRole(id);
	}


}
