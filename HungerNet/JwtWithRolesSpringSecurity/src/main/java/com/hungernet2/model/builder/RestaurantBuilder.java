package com.hungernet2.model.builder;

import com.hungernet2.model.entity.*;
public class RestaurantBuilder {

	private Restaurant restaurant = new Restaurant();

	public Restaurant build() { return restaurant;}

	public RestaurantBuilder withId(Integer id) {
		restaurant.setId(id);
		return this;
	}

	public RestaurantBuilder withName(String name) {
		restaurant.setName(name);
		return this;
	}

}
