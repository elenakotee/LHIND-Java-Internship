package com.springbootrest.service;

import com.springbootrest.model.dto.EmployeeDTO;
import com.springbootrest.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<EmployeeDTO> retrieveAllEmployees(Integer pageNumber, Integer pageSize);

	List<EmployeeDTO> retrieveEmployeesByFirstName(String firstName);

	List<EmployeeDTO> retrieveEmployeesByLastName(String firstName);

	EmployeeDTO storeEmployee(Employee employee);

	Boolean deleteEmployee(Integer id);

	Boolean clearDatabase();

}
