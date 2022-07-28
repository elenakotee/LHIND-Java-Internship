package com.hungernet2.model.builder;

import com.hungernet2.model.entity.Item;
import com.hungernet2.model.entity.Menu;

public class ItemBuilder {

	private Item item = new Item();

	public Item build() {
		return item;
	}

	public ItemBuilder withId(Integer id) {
		item.setId(id);
		return this;
	}

	public ItemBuilder withDescription(String description) {
		item.setDescription(description);
		return this;
	}

	public ItemBuilder withMenu(Menu menu) {
		item.setMenu(menu);
		return this;
	}


}

