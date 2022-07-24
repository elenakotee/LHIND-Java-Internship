package com.jwtwithrolesspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwtwithrolesspringsecurity.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
