package repository;

import model.entity.User;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

	List<User> findByUsername(String username);
	List<User> findByRole(String role);
}
