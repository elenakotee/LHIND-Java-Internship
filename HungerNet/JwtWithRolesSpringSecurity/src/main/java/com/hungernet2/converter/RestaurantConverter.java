package com.hungernet2.converter;


import com.hungernet2.model.builder.RestaurantBuilder;
import com.hungernet2.model.builder.RestaurantDTOBuilder;
import com.hungernet2.model.dto.RestaurantDTO;
import com.hungernet2.model.entity.*;

public class RestaurantConverter extends AbstractConverter<Restaurant, RestaurantDTO>{
	@Override
	public Restaurant toEntity(RestaurantDTO restaurantDTO) {
		return new RestaurantBuilder()
				.withId(restaurantDTO.getId())
				.withName(restaurantDTO.getName())
				.build();
	}

	@Override
	public RestaurantDTO toDTO(Restaurant restaurant) {
		return new RestaurantDTOBuilder()
				.withId(restaurant.getId())
				.withName(restaurant.getName())
				.build();
	}
}
