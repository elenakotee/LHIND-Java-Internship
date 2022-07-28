package com.hungernet2.model.builder;
import com.hungernet2.model.dto.ItemDTO;
import com.hungernet2.model.entity.*;

public class ItemDTOBuilder {

	private ItemDTO itemDTO = new ItemDTO();

	public ItemDTO build() {
		return itemDTO;
	}

	public ItemDTOBuilder withId(Integer id) {
		itemDTO.setId(id);
		return this;
	}

	public ItemDTOBuilder withDescription(String description) {
		itemDTO.setDescription(description);
		return this;
	}

	public ItemDTOBuilder withMenu(Menu menu) {
		itemDTO.setMenu(menu);
		return this;
	}

}
