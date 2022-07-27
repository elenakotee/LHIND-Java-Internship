package com.hungernet2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.hungernet2.model.entity.*;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	@Override
	Optional<Menu> findById(Integer integer);

	List<Menu> findByActive(Boolean active);

	Optional<Menu> findByName(String menuName);

	List<Menu> findByRestaurant(Restaurant restaurant);
}