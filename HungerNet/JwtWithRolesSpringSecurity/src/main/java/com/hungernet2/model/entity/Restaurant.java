package com.hungernet2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_id")
	private Integer id;

	@Column(name = "restaurant_name", unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")//, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Order> orders;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")//, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Menu> menus;

	@Column(name = "restaurant_manager", nullable = false, unique = true)
	private String managerUsername;


	public Restaurant(String name, String managerUsername) {
		this.name = name;
		this.managerUsername = managerUsername;
	}

	public Restaurant() {
	}

	public Restaurant(String name, Set<Order> orders, Set<Menu> menus, String managerUsername) {
		this.name = name;
		this.orders = orders;
		this.menus = menus;
		this.managerUsername = managerUsername;
	}

	public Restaurant(String name, Set<Order> orders, Set<Menu> menus) {
		this.name = name;
		this.orders = orders;
		this.menus = menus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}


	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	public Menu getActiveMenu() {
		for(Menu menu: menus) {
			if(menu.getActive()) {
				return menu;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Restaurant{" +
				"id=" + id +
				", name='" + name + '\'' +
				", orders=" + orders +
				", menus=" + menus +
				", managerUsername='" + managerUsername + '\'' +
				'}';
	}
}
