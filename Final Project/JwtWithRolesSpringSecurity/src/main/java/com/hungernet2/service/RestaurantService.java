package com.hungernet2.service;

import com.hungernet2.model.dto.RestaurantDTO;
import com.hungernet2.model.entity.Restaurant;
import com.hungernet2.model.entity.*;
import java.util.List;

public interface RestaurantService {

	List<RestaurantDTO> retrieveAllRestaurants();

	List<RestaurantDTO> retrieveAllRestaurantsPagination(Integer pageNumber, Integer pageSize);

	RestaurantDTO retrieveByManagerUsername(String managerUsername);

	RestaurantDTO storeRestaurant(Restaurant restaurant);

	void deleteRestaurant(Integer id);

	void clearDatabase();
}
