package com.hungernet2.controller;

import com.hungernet2.model.dto.UserDTO;
import com.hungernet2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hungernet2.model.entity.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	private final Logger logger = LogManager.getLogger();

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findAllButAdmin() {
		logger.debug("an admin listing all non-admin users \n");
		List<UserDTO> result = userService.retrieveAllUsersButAdmin();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{role}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> findByRole(@PathVariable("role") String roleName) {
		logger.debug("an admin listing all non-admin users and filtering them by role \n");
		List<UserDTO> result = userService.retrieveByRoleEnum(roleName);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> storeUser(@RequestBody JwtRequest jwtRequest) {
		logger.debug("an admin storing a user \n");
		return new ResponseEntity<>(userService.registerNewUserDTO(jwtRequest), HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
		logger.debug("an admin updating a user \n");
		return new ResponseEntity<>(userService.storeUser(user), HttpStatus.OK);
	}

	@DeleteMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@RequestParam(name = "id") Integer id) {
		logger.debug("an admin deleting a user \n");
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
	}

	@PostMapping("/assignRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> assignRoleToUser
			(@RequestParam(name = "username") String username, @RequestParam(name = "role") String roleName) {
		logger.debug("an admin assigning a role to a user \n");
		UserDTO userDTO =
				userService.assignRoleToUser(roleName, userService.retrieveByUsername(username).getId());
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}






