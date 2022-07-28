package com.hungernet2.repository;

import com.hungernet2.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.hungernet2.model.entity.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Override
	Optional<Order> findById(Integer integer);

	List<Order> findByOrderStatus(OrderStatus orderStatus);

	List<Order> findByUser(User user);

	List<Order> findByRestaurant(Restaurant restaurant);

}