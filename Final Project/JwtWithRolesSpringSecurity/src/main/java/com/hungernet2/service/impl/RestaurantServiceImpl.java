package com.hungernet2.service.impl;

import com.hungernet2.converter.AbstractConverter;
import com.hungernet2.converter.RestaurantConverter;
import com.hungernet2.model.dto.RestaurantDTO;
import com.hungernet2.repository.RestaurantRepository;
import com.hungernet2.service.RestaurantService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import com.hungernet2.model.entity.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final AbstractConverter<Restaurant, RestaurantDTO> restaurantConverter;

	public RestaurantServiceImpl(final RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
		this.restaurantConverter = new RestaurantConverter();
	}


	@Override
	public List<RestaurantDTO> retrieveAllRestaurants() {
		List<RestaurantDTO> result =
				restaurantRepository.findAll()
						.stream()
						.map(restaurantConverter::toDTO)
						.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<RestaurantDTO> retrieveAllRestaurantsPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<RestaurantDTO> result = new ArrayList<>();
		List<Restaurant> temp = restaurantRepository.findAll();
		temp.sort(Comparator.comparing(Restaurant::getId));

		for (int i = mult - pageSize; i < mult && i < temp.size(); i++) {
			result.add(restaurantConverter.toDTO(temp.get(i)));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public RestaurantDTO retrieveByManagerUsername(String managerUsername) {
		Restaurant restaurant = restaurantRepository.findByManagerUsername(managerUsername)
				.orElseThrow(NoSuchElementException::new);
		return restaurantConverter.toDTO(restaurant);
	}

	@Override
	public RestaurantDTO storeRestaurant(Restaurant restaurant) {
		return restaurantConverter.toDTO(restaurantRepository.save(restaurant));
	}

	@Override
	public void deleteRestaurant(Integer id) {
		if (restaurantRepository.existsById(id)) {
			restaurantRepository.deleteById(id);
		} else {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Override
	public void clearDatabase() {
		restaurantRepository.deleteAll();
	}
}