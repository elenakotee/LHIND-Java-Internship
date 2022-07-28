package com.hungernet2.controller;

import com.hungernet2.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.hungernet2.model.entity.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


	private final OrderService orderService;

	private final Logger logger = LogManager.getLogger();

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PutMapping("/changeStatus/{orderStatus}")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<String> changeOrderStatus(@RequestParam(name = "id") Integer orderId,
													@PathVariable("orderStatus") String orderStatusString){
		logger.debug("restaurant manager changing an order's status \n");
		orderService.changeOrderStatus(orderId, orderStatusString);
		return new ResponseEntity<>("OrderStatus changed successfully", HttpStatus.OK);
	}

	@GetMapping("/details")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<String> getOrderDetails(@RequestParam(name = "id") Integer id) {
		logger.debug("restaurant manager getting an order's details \n");
		return new ResponseEntity<>(orderService.getOrderDetails(id), HttpStatus.OK);
	}


	@GetMapping("/{restName}")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<List<Order>> findAllOrdersForRestaurant(@PathVariable("restName") String restName) {
		logger.debug("restaurant manager listing all their restaurant's orders \n");
		List<Order> result = orderService.retrieveByRestaurant(restName);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{restName}/{status}")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<List<Order>> findByOrderStatus(@PathVariable("status") String status,
														 @PathVariable("restName") String restName) {
		logger.debug("restaurant manager filtering orders by status \n");
		List<Order> result = orderService.retrieveByRestaurantAndStatus(restName, status);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/makeorder")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<Order> makeOrder(@RequestBody List<Integer> items) {
		logger.debug("a client making an order \n");
		return new ResponseEntity<>(orderService.makeOrder(items),HttpStatus.CREATED);
	}

	@GetMapping("/getstatus")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<String> getOrderStatus(@RequestParam(name = "id") Integer id) {
		logger.debug("a client checking an order's status \n");
		return new ResponseEntity<>(orderService.getOrderStatus(id), HttpStatus.OK);
	}

	@GetMapping("/myorders")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<List<Order>> filterByClient() {
		logger.debug("a client checking their orders \n");
		List<Order> result = orderService.retrieveCurrentUserOrders();
		return ResponseEntity.ok(result);
	}




}
