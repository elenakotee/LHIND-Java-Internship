package com.hungernet2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hungernet2.model.entity.*;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(String roleName);
}
