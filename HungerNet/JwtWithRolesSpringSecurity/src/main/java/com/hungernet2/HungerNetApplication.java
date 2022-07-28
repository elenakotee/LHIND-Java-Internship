package com.hungernet2;

import com.hungernet2.model.entity.*;
import com.hungernet2.repository.RoleRepository;
import com.hungernet2.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class HungerNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(HungerNetApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService, ItemService itemService,
								 MenuService menuService, OrderService orderService,
								 RestaurantService restaurantService, RoleService roleService) {

		return (args -> {
			Role role1 = new Role("ADMIN", "admin!");
			Role role2 = new Role("CLIENT", "client!");
			Role role3 = new Role("RESTAURANT_MANAGER", "manager!");
			roleService.storeRole(role1);
			roleService.storeRole(role2);
			roleService.storeRole(role3);
			HashSet<Role> roleSet1 = new HashSet<>();
			HashSet<Role> roleSet2 = new HashSet<>();
			HashSet<Role> roleSet3 = new HashSet<>();
			roleSet1.add(role1);
			roleSet2.add(role2);
			roleSet3.add(role3);

			User user1 = new User("manager", "manager", roleSet3);
			User user4 = new User("manager2", "manager2", roleSet3);
			User user2 = new User("admin", "admin", roleSet1);
			User user3 = new User("client", "client", roleSet2);
			User user5 = new User("client2", "client2", roleSet2);
			User user6 = new User("newManager", "newManager", roleSet3);
			userService.registerNewUser(user1);
			userService.registerNewUser(user2);
			userService.registerNewUser(user3);
			userService.registerNewUser(user4);
			userService.registerNewUser(user5);
			userService.registerNewUser(user6);

			Restaurant rest1 = new Restaurant("rest1","manager");
			Restaurant rest2 = new Restaurant("rest2","manager2");
			restaurantService.storeRestaurant(rest1);
			restaurantService.storeRestaurant(rest2);

			Menu menu1 = new Menu("menu1", rest1);
			menu1.setActive(true);
			Menu menu2 = new Menu("menu2", rest2);
			Menu menu3 = new Menu("menu3", rest2);
			menu3.setActive(true);
			menuService.storeMenu(menu1);
			menuService.storeMenu(menu2);
			menuService.storeMenu(menu3);


			Item item1 = new Item("item1", menu1);
			Item item2 = new Item("item2", menu1);
			Item item3 = new Item("item3", menu2);
			Item item4 = new Item("item4", menu2);
			Item item5 = new Item("item5", menu3);
			itemService.storeItem(item1);
			itemService.storeItem(item2);
			itemService.storeItem(item3);
			itemService.storeItem(item4);
			itemService.storeItem(item5);
			Set<Item> itemSet1 = new HashSet<>();
			Set<Item> itemSet2 = new HashSet<>();
			Set<Item> itemSet3 = new HashSet<>();
			List<Item> itemList1 = new ArrayList<>();
			List<Item> itemList2 = new ArrayList<>();
			List<Item> itemList3 = new ArrayList<>();
			itemSet1.add(item1);
			itemSet1.add(item2);
			itemSet2.add(item3);
			itemSet2.add(item4);
			itemSet3.add(item5);
			itemList1.add(item1);
			itemList1.add(item2);
			itemList2.add(item3);
			itemList2.add(item4);
			itemList3.add(item5);


			Order order1 = new Order(user3);
			Order order2 = new Order(user3);
			Order order3 = new Order(user5);
			order1.setItems(itemList1);
			order2.setItems(itemList2);
			order3.setItems(itemList3);
			orderService.storeOrder(order1);
			orderService.storeOrder(order2);
			orderService.storeOrder(order3);


		});

	}

}
