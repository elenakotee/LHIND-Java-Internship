package com.hungernet2.controller;

import com.hungernet2.model.entity.Menu;
import com.hungernet2.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

	private final MenuService menuService;

	private final Logger logger = LogManager.getLogger();

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}


	@GetMapping("/{restName}")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<List<Menu>> findAll(@PathVariable("restName") String restName) {
		logger.debug("restaurant manager listing all restaurant menus \n");
		List<Menu> result = menuService.retrieveByManager(restName);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/insert")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<Menu> storeMenu(@RequestParam(name = "menuName") String menuName) {
		logger.debug("restaurant manager storing a menu \n");
		return new ResponseEntity<>(menuService.storeByMenuName(menuName), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<Menu>updateMenu(@RequestParam(name = "menuName") String menuName) {
		logger.debug("restaurant manager updating a menu \n");
		return new ResponseEntity<>(menuService.storeByMenuName(menuName), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('RESTAURANT_MANAGER')")
	public ResponseEntity<String> deleteMenu(@RequestParam(name = "id") Integer id) {
		logger.debug("restaurant manager deleting a menu \n");
		menuService.deleteMenu(id);
		return new ResponseEntity<>("Menu deleted successfully", HttpStatus.ACCEPTED);
	}


	@GetMapping("/list")
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<List<Menu>> findAllActive() {
		logger.debug("client listing all active menus \n");
		return new ResponseEntity<>(menuService.retrieveAllActiveMenus(), HttpStatus.OK);
	}


}
