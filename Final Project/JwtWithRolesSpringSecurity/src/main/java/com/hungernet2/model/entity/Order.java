package com.hungernet2.model.entity;

import com.hungernet2.model.enums.OrderStatus;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer id;


	@Enumerated(value = EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus = OrderStatus.CREATED;

	@Column(name = "order_date")
	@Temporal(value = TemporalType.DATE)
	private Date orderDate = new Date();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(
			name = "restaurant_id",
			referencedColumnName = "restaurant_id"
	)
	private Restaurant restaurant;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "order_items",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> items;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "user_username",
			referencedColumnName = "user_username"
	)
	private User user;


	public Order() {
	}

	public Order(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", orderStatus=" + orderStatus +
				", orderDate=" + orderDate +
				", restaurant=" + restaurant +
				", items=" + items +
				", user=" + user +
				'}';
	}
}
