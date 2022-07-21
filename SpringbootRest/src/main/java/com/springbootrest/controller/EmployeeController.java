package com.springbootrest.controller;


import com.springbootrest.model.dto.EmployeeDTO;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;


	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;

	}
	@GetMapping
	public List<EmployeeDTO> findAll() {
		return employeeService.retrieveAllEmployees(1, 10);
	}

	@GetMapping("/{firstname}")
	public List<EmployeeDTO> findByFirstName(@PathVariable("firstname") String firstName) {
		return employeeService.retrieveEmployeesByFirstName(firstName);

	}

	@PostMapping
	public EmployeeDTO storeEmployee(@RequestBody Employee employee) {
		return employeeService.storeEmployee(employee);
	}

	@PutMapping
	public EmployeeDTO updateEmployee(@RequestBody Employee employee) {
		return employeeService.storeEmployee(employee);
	}

	@DeleteMapping
	public Boolean deleteEmployee(@RequestParam(name = "id") Integer id) {
		return employeeService.deleteEmployee(id);
	}



}
