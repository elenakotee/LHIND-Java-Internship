package com.hungernet2.service;

import com.hungernet2.model.entity.*;
import com.hungernet2.model.requestentities.RequestRestaurant;

import java.util.List;

public interface MenuService {

	List<Menu> retrieveAllMenus();

	List<Menu> retrieveAllMenusPagination(Integer pageNumber, Integer pageSize);

	List<Menu> retrieveAllActiveMenus();

	List<Menu> retrieveAllActiveMenusPagination(Integer pageNumber, Integer pageSize);

	List<Menu> retrieveByRestaurant(RequestRestaurant requestRestaurant);

	Menu retrieveActiveMenuByRestaurant(Restaurant restaurant);

	Menu retrieveByMenuName(String menuName);

	Menu storeMenu(Menu menu);

	void deleteMenu(Integer id);

	void clearDatabase();
}
