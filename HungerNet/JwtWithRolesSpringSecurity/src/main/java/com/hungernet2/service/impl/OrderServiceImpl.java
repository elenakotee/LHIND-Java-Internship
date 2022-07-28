package com.hungernet2.service.impl;

import com.hungernet2.exception.EmptyInputException;
import com.hungernet2.exception.NoCredentialsException;
import com.hungernet2.model.enums.OrderStatus;
import com.hungernet2.repository.ItemRepository;
import com.hungernet2.repository.OrderRepository;
import com.hungernet2.repository.RestaurantRepository;
import com.hungernet2.repository.UserRepository;
import com.hungernet2.service.JwtService;
import com.hungernet2.service.OrderService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hungernet2.model.entity.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final RestaurantRepository restaurantRepository;

	private final ItemRepository itemRepository;

	private final UserRepository userRepository;

	public OrderServiceImpl(final OrderRepository orderRepository,
							final RestaurantRepository restaurantRepository,
							final ItemRepository itemRepository,
							final UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.restaurantRepository = restaurantRepository;
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Order> retrieveAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> retrieveAllOrdersPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<Order> result = new ArrayList<>();
		List<Order> temp = orderRepository.findAll();
		temp.sort(Comparator.comparing(Order::getId));

		for(int i = mult - pageSize; i < mult&&i<temp.size(); i++) {
			result.add(temp.get(i));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}


	@Override
	public List<Order> retrieveByOrderStatus(OrderStatus orderStatus) {
		List<Order> result =  orderRepository.findByOrderStatus(orderStatus);
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<Order> retrieveByUser(User user) {
		List<Order> result = orderRepository.findByUser(user);
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		result.sort(Comparator.comparing(Order::getOrderDate));
		return result;
	}

	@Override
	public void changeOrderStatus(Integer orderId, String status) {
		Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
		if(!checkManager(order)) {
			throw new NoCredentialsException();
		}
		OrderStatus orderStatus;
		switch(status) {
			case "approve" -> orderStatus = OrderStatus.APPROVED;
			case "reject" -> orderStatus = OrderStatus.REJECTED;
			case "prepare" -> orderStatus = OrderStatus.PREPARED;
			case "waiting" -> orderStatus = OrderStatus.WAITING_FOR_DELIVERY;
			case "deliver" -> orderStatus = OrderStatus.DELIVERED;
			default -> throw new NoCredentialsException();
		}
		order.setOrderStatus(orderStatus);
		storeOrder(order);
	}

	@Override
	public List<Order> retrieveByRestaurantAndStatus(String restName, String status) {
		Restaurant restaurant = restaurantRepository.findByName(restName)
				.orElseThrow(NoSuchElementException::new);
		if(!checkManager2(restaurant)) {
			throw new NoCredentialsException();
		}
		List<Order> temp = orderRepository.findByRestaurant(restaurant);
		List<Order> result = new ArrayList<>();
		if(temp.isEmpty()) {
			throw new NoSuchElementException();
		}
		for(Order order: temp) {
			if(order.getOrderStatus().toString().equalsIgnoreCase(status)) {
				result.add(order);
			}
		}
		return result;
	}

	@Override
	public List<Order> retrieveByRestaurant(String restName) {
		Restaurant restaurant = restaurantRepository.findByName(restName)
				.orElseThrow(NoSuchElementException::new);
		if(!checkManager2(restaurant)) {
			throw new NoCredentialsException();
		}
		List<Order> result = orderRepository.findByRestaurant(restaurant);
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Order makeOrder(List<Integer> items) {
		if(items.isEmpty()) {
			throw new EmptyInputException("Empty order list");
		}
		Restaurant restaurant = itemRepository.findById(items.get(0))
				.orElseThrow(NoSuchElementException::new)
				.getMenu().getRestaurant();
		String managerUsername = restaurant.getManagerUsername();
		List<Item> itemList = new ArrayList<>();

		//check if all items come from the same restaurant
		for(Integer idOfItem: items) {
			Item item = itemRepository.findById(idOfItem).orElseThrow(NoSuchElementException::new);
			if(!item.getMenu().getRestaurant().getManagerUsername()
					.equals(managerUsername)) {
				throw new NoCredentialsException();
			} else {
				itemList.add(item);
			}
		}

		User user = userRepository.findByUsername(JwtService.getCurrentUsername())
				.orElseThrow(NoSuchElementException::new);
		Order order = new Order(restaurant, itemList, user);
		storeOrder(order);
		return order;
	}

	@Override
	public String getOrderDetails(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
		if(!checkManager(order)) {
			throw new NoCredentialsException();
		}
		return order.toString();
	}

	@Override
	public String getOrderStatus(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
		if(!checkClient(order)) {
			throw new NoCredentialsException();
		}
		return order.getOrderStatus().toString();
	}

	@Override
	public List<Order> retrieveCurrentUserOrders() {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userRepository.findByUsername(currentUsername).orElseThrow(NoSuchElementException::new);
		return retrieveByUser(currentUser);
	}


	@Override
	public Order storeOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
		} else {
			throw new EmptyResultDataAccessException(0);
		}
	}

	@Override
	public void clearDatabase() {
		orderRepository.deleteAll();
	}

	private Boolean checkManager(Order order) {
		return order.getRestaurant().getManagerUsername()
				.equals(JwtService.getCurrentUsername());
	}

	private Boolean checkClient(Order order) {
		return order.getUser().getUsername()
				.equals(JwtService.getCurrentUsername());
	}

	private Boolean checkManager2(Restaurant restaurant) {
		return restaurant.getManagerUsername().equals(JwtService.getCurrentUsername());
	}
}
