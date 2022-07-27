package com.hungernet2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hungernet2.model.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	List<User> findByRolesIn(Set<Role> roles);

}