package com.hungernet2.controller;

import com.hungernet2.model.dto.ItemDTO;
import com.hungernet2.model.entity.Item;
import com.hungernet2.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	private final Logger logger = LogManager.getLogger();

	@GetMapping("/{menuName}")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<List<ItemDTO>> findAll(@PathVariable(value = "menuName") String menuName) {
		logger.debug("restaurant manager listing all of a menu's items \n");
		List<ItemDTO> result = itemService.retrieveByMenuName(menuName);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/insert")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<ItemDTO> storeItem(@RequestParam(name = "itemName")String itemName
			,@RequestParam(name = "menuId")Integer menuId ) {
		logger.debug("restaurant manager storing an item \n");
		return ResponseEntity.ok(itemService.storeItemByNameAndMenuId(itemName, menuId));
	}


	@PutMapping("/update")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<ItemDTO> updateItem(@RequestParam(name = "itemName")String itemName
			,@RequestParam(name = "menuId")Integer menuId ) {
		logger.debug("restaurant manager updating an item \n");
		return ResponseEntity.ok(itemService.storeItemByNameAndMenuId(itemName, menuId));
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<String> deleteItem(@RequestParam(name = "id") Integer id) {
		logger.debug("restaurant manager deleting an item \n");
		itemService.deleteItem(id);
		return new ResponseEntity<>("Item deleted successfully", HttpStatus.ACCEPTED);
	}

}
