package com.hungernet2.converter;


import com.hungernet2.model.builder.ItemBuilder;
import com.hungernet2.model.builder.ItemDTOBuilder;
import com.hungernet2.model.dto.ItemDTO;
import com.hungernet2.model.entity.*;

public class ItemConverter extends AbstractConverter<Item, ItemDTO>{
	@Override
	public Item toEntity(ItemDTO itemDTO) {
		return new ItemBuilder()
				.withId(itemDTO.getId())
				.withDescription(itemDTO.getDescription())
				.withMenu(itemDTO.getMenu())
				.build();
	}

	@Override
	public ItemDTO toDTO(Item item) {
		return new ItemDTOBuilder()
				.withId(item.getId())
				.withDescription(item.getDescription())
				.withMenu(item.getMenu())
				.build();
	}
}
