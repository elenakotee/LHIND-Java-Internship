package com.jwtwithrolesspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwtwithrolesspringsecurity.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
