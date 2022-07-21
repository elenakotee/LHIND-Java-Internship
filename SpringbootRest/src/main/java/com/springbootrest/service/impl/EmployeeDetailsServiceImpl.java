package com.springbootrest.service.impl;


import com.springbootrest.model.entity.Employee;
import com.springbootrest.model.entity.EmployeeDetails;
import com.springbootrest.repository.EmployeeDetailsRepository;
import com.springbootrest.service.EmployeeDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	private final EmployeeDetailsRepository employeeDetailsRepository;

	public EmployeeDetailsServiceImpl(EmployeeDetailsRepository employeeDetailsRepository) {
		this.employeeDetailsRepository = employeeDetailsRepository;
	}

	@Override
	public List<EmployeeDetails> retrieveAllEmployeeDetails() {

		return employeeDetailsRepository.findAll();
	}

	@Override
	public EmployeeDetails storeEmployeeDetails(EmployeeDetails employeeDetails) {
		return employeeDetailsRepository.save(employeeDetails);
	}

	@Override
	public Boolean deleteEmployeeDetails(Integer id) {
		employeeDetailsRepository.deleteById(id);
		return true;
	}

	@Override
	public Boolean clearDatabase() {
		employeeDetailsRepository.deleteAll();
		return true;
	}

	@Override
	public EmployeeDetails retrieveByEmail(String email) {
		return employeeDetailsRepository.findByEmail(email);
	}

	@Override
	public EmployeeDetails retrieveByPhoneNumber(String phoneNumber) {
		return employeeDetailsRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public EmployeeDetails retrieveByEmployee(Employee employee) {
		return employeeDetailsRepository.findByEmployee(employee);
	}
}
