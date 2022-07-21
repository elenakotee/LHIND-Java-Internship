package com.springbootrest.controller;


import com.springbootrest.model.entity.Employee;
import com.springbootrest.model.entity.EmployeeDetails;
import com.springbootrest.service.EmployeeDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeedetails")
public class EmployeeDetailsController {

	private final EmployeeDetailsService employeeDetailsService;

	public EmployeeDetailsController(EmployeeDetailsService employeeDetailsService) {
		this.employeeDetailsService = employeeDetailsService;
	}

	@GetMapping
	public List<EmployeeDetails> findAll() {
		return employeeDetailsService.retrieveAllEmployeeDetails();
	}

	@GetMapping("/{email}")
	public EmployeeDetails findByEmail(@PathVariable("email") String email) {
		return employeeDetailsService.retrieveByEmail(email);
	}

	@GetMapping("/{phonenumber}")
	public EmployeeDetails findByPhoneNumber(@PathVariable("phonenumber") String phoneNumber) {
		return employeeDetailsService.retrieveByPhoneNumber(phoneNumber);
	}

	@GetMapping("/{employee}")
	public EmployeeDetails findByEmployee(@PathVariable("employee") Employee employee) {
		return employeeDetailsService.retrieveByEmployee(employee);
	}


	@PostMapping
	public EmployeeDetails storeEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) {
		return employeeDetailsService.storeEmployeeDetails(employeeDetails);
	}


	@PutMapping
	public EmployeeDetails updateEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) {
		return employeeDetailsService.storeEmployeeDetails(employeeDetails);
	}

	@DeleteMapping
	public Boolean deleteEmployeeDetails(@RequestParam(name = "id") Integer id) {
		return employeeDetailsService.deleteEmployeeDetails(id);
	}





}
