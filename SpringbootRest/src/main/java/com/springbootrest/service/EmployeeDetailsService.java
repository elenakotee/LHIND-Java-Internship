package com.springbootrest.service;

import com.springbootrest.model.entity.Employee;
import com.springbootrest.model.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeDetailsService {

	List<EmployeeDetails> retrieveAllEmployeeDetails();

	EmployeeDetails storeEmployeeDetails(EmployeeDetails employeeDetails);

	Boolean deleteEmployeeDetails(Integer id);

	Boolean clearDatabase();

	EmployeeDetails retrieveByEmail(String email);

	EmployeeDetails retrieveByPhoneNumber(String phoneNumber);

	EmployeeDetails retrieveByEmployee(Employee employee);

}
