package com.hungernet2.service;

import com.hungernet2.model.entity.*;

import java.util.List;

public interface MenuService {

	List<Menu> retrieveAllMenus();

	List<Menu> retrieveAllMenusPagination(Integer pageNumber, Integer pageSize);

	List<Menu> retrieveAllActiveMenus();

	List<Menu> retrieveAllActiveMenusPagination(Integer pageNumber, Integer pageSize);

	List<Menu> retrieveByManager(String restName);

	Menu retrieveActiveMenuByRestaurant(Restaurant restaurant);

	Menu retrieveByMenuName(String menuName);

	Menu retrieveByMenuId(Integer id);

	Menu storeByMenuName(String menuName);

	Menu storeMenu(Menu menu);

	void deleteMenu(Integer id);

	void clearDatabase();
}
