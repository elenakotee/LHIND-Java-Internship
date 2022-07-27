package com.hungernet2.service;

import com.hungernet2.model.entity.*;
import com.hungernet2.model.enums.OrderStatus;
import com.hungernet2.model.requestentities.RequestRestaurant;

import java.util.List;

public interface OrderService {

	List<Order> retrieveAllOrders();

	List<Order> retrieveAllOrdersPagination(Integer pageNumber, Integer pageSize);

	List<Order> retrieveByOrderStatus(OrderStatus orderStatus);

	List<Order> retrieveByUser(User user);

	List<Order> retrieveByRestaurant(RequestRestaurant requestRestaurant);

	Order makeOrder(List<Integer> items, Integer id);

	List<Order> retrieveByRestaurantAndStatus(RequestRestaurant requestRestaurant, String status);

	void changeOrderStatus(Integer orderId, String status);

	List<Order> retrieveCurrentUserOrders();

	String getOrderStatus(Integer id);

	String getOrderDetails(Integer id);

	Order storeOrder(Order order);

	void deleteOrder(Integer id);

	void clearDatabase();
}
