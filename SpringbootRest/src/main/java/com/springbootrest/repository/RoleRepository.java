package com.springbootrest.repository;

import com.springbootrest.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootrest.model.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findByRole(RoleEnum role);

	List<Role> findByDescription(String description);
}