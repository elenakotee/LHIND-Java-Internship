package com.hungernet2.service.impl;

import com.hungernet2.converter.AbstractConverter;
import com.hungernet2.converter.ItemConverter;
import com.hungernet2.exception.NoCredentialsException;
import com.hungernet2.model.dto.ItemDTO;
import com.hungernet2.repository.ItemRepository;
import com.hungernet2.service.ItemService;
import com.hungernet2.service.MenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.hungernet2.model.entity.*;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
	private final MenuService menuService;
	private final AbstractConverter<Item, ItemDTO> itemConverter;

	public ItemServiceImpl(ItemRepository itemRepository, MenuService menuService) {
		this.itemRepository = itemRepository;
		this.menuService = menuService;
		this.itemConverter = new ItemConverter();
	}


	@Override
	public List<ItemDTO> retrieveAllItems() {
		List<ItemDTO> result =
				itemRepository.findAll()
						.stream()
						.map(itemConverter::toDTO)
						.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<ItemDTO> retrieveAllItemsPagination(Integer pageNumber, Integer pageSize) {
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<ItemDTO> result = new ArrayList<>();
		List<Item> temp = itemRepository.findAll();
		temp.sort(Comparator.comparing(Item::getId));

		for (int i = mult - pageSize; i < mult && i < temp.size(); i++) {
			result.add(itemConverter.toDTO(temp.get(i)));
		}
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public List<ItemDTO> retrieveByMenu(Menu menu) {
		List <ItemDTO> result =
				itemRepository.findByMenu(menu)
						.stream()
						.map(itemConverter::toDTO)
						.collect(Collectors.toList());
		if(result.isEmpty()) {
			throw new NoSuchElementException();
		}
		if(!checkManagerUsername(result.get(0).getMenu())) {
			throw new NoCredentialsException();
		}
		return result;
	}

	@Override
	public List<ItemDTO> retrieveByMenuName(String menuName) {
		Menu menu = menuService.retrieveByMenuName(menuName);
		if(!checkManagerUsername(menu)){
			throw new NoCredentialsException();
		}
		return menu.getItems().stream().map(itemConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public ItemDTO storeItem(Item item) {
		return itemConverter.toDTO(itemRepository.save(item));
	}

	@Override
	public void deleteItem(Integer id) {

		Item item = itemRepository.findById(id).orElseThrow(NoSuchElementException::new);
		if(!checkManagerUsername(item.getMenu())){
			throw new NoCredentialsException();
		}
		itemRepository.deleteById(id);
	}

	@Override
	public void clearDatabase() {
		itemRepository.deleteAll();
	}

	private Boolean checkManagerUsername(Menu menu) {
		return menu.getRestaurant().getManagerUsername()
				.equals(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
