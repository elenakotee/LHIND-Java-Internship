package com.hungernet2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menu_id")
	private Integer id;

	@Column(name = "menu_name", unique = true)
	private String name;

	@Column(name = "menu_is_active")
	private Boolean active;

	@Column(name = "active_time")
	private LocalTime startTime;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(
			name = "restaurant_id",
			referencedColumnName = "restaurant_id"
	)
	private Restaurant restaurant;


	@JsonIgnore
	@OneToMany(mappedBy = "menu")//, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Item> items;

	public Menu(String name) {
		this.name = name;
		this.active = false;
	}


	public Menu(String name, Set<Item> items) {
		this.name = name;
		this.items = items;
		this.active = false;
	}

	public Menu(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
		this.active = false;
	}

	public Menu() {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", name='" + name + '\'' +
				", active=" + active +
				", startTime=" + startTime +
				", restaurant=" + restaurant +
				", items=" + items +
				'}';
	}
}
