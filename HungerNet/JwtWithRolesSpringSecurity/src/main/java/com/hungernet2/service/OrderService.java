package com.hungernet2.service;

import com.hungernet2.model.entity.*;
import com.hungernet2.model.enums.OrderStatus;

import java.util.List;

public interface OrderService {

	List<Order> retrieveAllOrders();

	List<Order> retrieveAllOrdersPagination(Integer pageNumber, Integer pageSize);

	List<Order> retrieveByOrderStatus(OrderStatus orderStatus);

	List<Order> retrieveByUser(User user);

	List<Order> retrieveByRestaurant(String restName);

	Order makeOrder(List<Integer> items);

	List<Order> retrieveByRestaurantAndStatus(String restName, String status);

	void changeOrderStatus(Integer orderId, String status);

	List<Order> retrieveCurrentUserOrders();

	String getOrderStatus(Integer id);

	String getOrderDetails(Integer id);

	Order storeOrder(Order order);

	void deleteOrder(Integer id);

	void clearDatabase();
}
