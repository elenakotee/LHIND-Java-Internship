package com.springbootrest.service;

import com.springbootrest.model.enums.RoleEnum;
import com.springbootrest.model.entity.Role;

import java.util.List;

public interface RoleService {

	List<Role> retrieveAllRoles();

	List<Role> retrieveRolesByRole(RoleEnum role);

	List<Role> retrieveRolesByDescription(String description);

	Role storeRole(Role role);

	Boolean deleteRole(Integer id);

	Boolean clearDatabase();


}