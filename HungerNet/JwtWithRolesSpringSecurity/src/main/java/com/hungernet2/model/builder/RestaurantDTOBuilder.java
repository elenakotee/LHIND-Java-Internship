package com.hungernet2.model.builder;

import com.hungernet2.model.dto.RestaurantDTO;
public class RestaurantDTOBuilder {

	private RestaurantDTO restaurantDTO = new RestaurantDTO();

	public RestaurantDTO build() { return restaurantDTO;}

	public RestaurantDTOBuilder withId(Integer id) {
		restaurantDTO.setId(id);
		return this;
	}

	public RestaurantDTOBuilder withName(String name) {
		restaurantDTO.setName(name);
		return this;
	}



}
