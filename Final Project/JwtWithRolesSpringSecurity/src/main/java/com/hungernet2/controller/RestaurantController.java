package com.hungernet2.controller;

import com.hungernet2.model.dto.RestaurantDTO;
import com.hungernet2.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.hungernet2.model.entity.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;

	private final Logger logger = LogManager.getLogger();

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<RestaurantDTO>> findAll() {
		logger.debug("a user listing all restaurants \n");
		List<RestaurantDTO> result = restaurantService.retrieveAllRestaurants();
		return ResponseEntity.ok(result);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RestaurantDTO> storeRestaurant(@RequestBody Restaurant restaurant) {
		logger.debug("an admin adding a restaurant \n");
		return new ResponseEntity<>(restaurantService.storeRestaurant(restaurant), HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RestaurantDTO>updateRestaurant(@RequestBody Restaurant restaurant) {
		logger.debug("an admin updating a restaurant \n");
		return new ResponseEntity<>(restaurantService.storeRestaurant(restaurant), HttpStatus.OK);
	}

	@DeleteMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteRestaurant(@RequestParam(name = "id") Integer id) {
		restaurantService.deleteRestaurant(id);
		logger.debug("an admin deleting a restaurant \n");
		return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.ACCEPTED);
	}


}