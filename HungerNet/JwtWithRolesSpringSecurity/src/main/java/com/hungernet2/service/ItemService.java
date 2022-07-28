package com.hungernet2.service;

import com.hungernet2.model.dto.ItemDTO;
import com.hungernet2.model.entity.*;

import java.util.List;

public interface ItemService {

	List<ItemDTO> retrieveAllItems();

	List<ItemDTO> retrieveAllItemsPagination(Integer pageNumber, Integer pageSize);

	List<ItemDTO> retrieveByMenu(Menu menu);

	List<ItemDTO> retrieveByMenuName(String menuName);

	ItemDTO storeItemByNameAndMenuId(String itemName, Integer menuId);

	ItemDTO storeItem(Item item);

	void deleteItem(Integer id);

	void clearDatabase();
}
