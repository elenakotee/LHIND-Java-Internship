package com.hungernet2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.hungernet2.model.entity.*;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	@Override
	Optional<Restaurant> findById(Integer integer);

	Optional<Restaurant> findByManagerUsername(String managerUsername);

	Optional<Restaurant> findByName(String restaurantName);

}
