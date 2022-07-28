package com.hungernet2.service;

import com.hungernet2.model.entity.*;
import java.util.List;

public interface RoleService {

	List<Role> retrieveAllRoles();

	Role retrieveByRole(String role);

	Role storeRole(Role role);

	void deleteRole(Integer id);

	void clearDatabase();

}

