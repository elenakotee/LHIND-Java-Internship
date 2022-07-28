package com.hungernet2.service.impl;

import com.hungernet2.exception.NoCredentialsException;
import com.hungernet2.model.requestentities.RequestRestaurant;
import com.hungernet2.repository.MenuRepository;
import com.hungernet2.repository.RestaurantRepository;
import com.hungernet2.service.MenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hungernet2.model.entity.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;

	private final RestaurantRepository restaurantRepository;

	public MenuServiceImpl(final MenuRepository menuRepository, final RestaurantRepository restaurantRepository) {
		this.menuRepository = menuRepository;
		this.restaurantRepository = restaurantRepository;
	}


	@Override
	public List<Menu> retrieveAllMenus() {

		List<Menu> result = menuRepository.findAll();
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<Menu> retrieveAllMenusPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<Menu> result = new ArrayList<>();
		List<Menu> temp = menuRepository.findAll();
		temp.sort(Comparator.comparing(Menu::getId));

		for(int i = mult - pageSize; i < mult&&i<temp.size(); i++) {
			result.add(temp.get(i));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<Menu> retrieveAllActiveMenus() {
		List<Menu> result = menuRepository.findByActive(true);
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<Menu> retrieveAllActiveMenusPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<Menu> result = new ArrayList<>();
		List<Menu> temp = menuRepository.findByActive(true);
		temp.sort(Comparator.comparing(Menu::getId));

		for(int i = mult - pageSize; i < mult&&i<temp.size(); i++) {
			result.add(temp.get(i));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Menu retrieveByMenuName(String menuName) {
		return menuRepository.findByName(menuName).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<Menu> retrieveByRestaurant(RequestRestaurant requestRestaurant) {
		Restaurant restaurant = restaurantRepository.findByName(requestRestaurant.getName())
				.orElseThrow(NoSuchElementException::new);
		if(!checkManager1(requestRestaurant, restaurant)) {
			throw new NoCredentialsException();
		}
		List<Menu> result = menuRepository.findByRestaurant(restaurant);
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Menu retrieveActiveMenuByRestaurant(Restaurant restaurant) {
		List<Menu> temp = menuRepository.findByRestaurant(restaurant);
		List<Menu> result = new ArrayList<>();
		for(Menu menu : temp) {
			if(menu.getActive()) {
				//since there can only be one active menu at a time
				return menu;
			}
		}
		throw new NoSuchElementException();
	}


	@Override
	public Menu storeMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public void deleteMenu(Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(NoSuchElementException::new);
		if(!checkManager2(menu.getRestaurant())) {
			throw new NoSuchElementException();
		}
		menuRepository.deleteById(id);
	}

	@Override
	public void clearDatabase() {
		menuRepository.deleteAll();
	}

	private Boolean checkManager1(RequestRestaurant requestRestaurant, Restaurant restaurant) {
		return requestRestaurant.getManagerUsername().equals(restaurant.getManagerUsername());
	}

	private Boolean checkManager2(Restaurant restaurant) {
		return restaurant.getManagerUsername()
				.equals(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
