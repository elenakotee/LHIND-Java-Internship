package com.hungernet2.model.dto;

import com.hungernet2.model.entity.Menu;

public class ItemDTO {

	private Integer id;

	private String description;

	private Menu menu;


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


	@Override
	public String toString() {
		return "ItemDTO{" +
				"id=" + id +
				", description='" + description + '\'' +
				", menu=" + menu +
				'}';
	}
}

