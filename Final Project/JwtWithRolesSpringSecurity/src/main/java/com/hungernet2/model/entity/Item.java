package com.hungernet2.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Integer id;

	@Column(name = "item_description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "menu_id",
			referencedColumnName = "menu_id"
	)
	private Menu menu;

	@ManyToMany(mappedBy = "items")
	List<Order> orders;

	public Item() {
	}

	public Item(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", description='" + description + '\'' +
				", menu=" + menu +
				", orders=" + orders +
				'}';
	}
}
