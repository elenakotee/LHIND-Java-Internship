package com.springbootrest.service.impl;

import com.springbootrest.converter.AbstractConverter;
import com.springbootrest.converter.EmployeeConverter;
import com.springbootrest.model.dto.EmployeeDTO;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.repository.EmployeeRepository;
import com.springbootrest.service.EmployeeService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {



	private final EmployeeRepository employeeRepository;
	private final AbstractConverter<Employee, EmployeeDTO> employeeConverter;

	public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		this.employeeConverter = new EmployeeConverter();
	}

	@Override
	public List<EmployeeDTO> retrieveAllEmployees(Integer pageNumber, Integer pageSize){
		Integer mult = Math.multiplyExact(pageNumber, pageSize);
		List<EmployeeDTO> result = new ArrayList<>();
		List<Employee> temp = employeeRepository.findAll();
		temp.sort(Comparator.comparing(Employee::getId));

		for(int i = mult - pageSize; i < mult&&i<temp.size(); i++) {
			result.add(employeeConverter.toDTO(temp.get(i)));
		}
		return result;
	}


	@Override
	public List<EmployeeDTO> retrieveEmployeesByFirstName(@Param("firstName") String firstName) {
		return employeeRepository.findByFirstName(firstName)
				.stream()
				.map(employeeConverter::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> retrieveEmployeesByLastName(@Param("lastName") String lastName) {
		return employeeRepository.findByLastName(lastName)
				.stream()
				.map(employeeConverter::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO storeEmployee(Employee employee){
		return employeeConverter.toDTO(employeeRepository.save(employee));
	}

	@Override
	public Boolean deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
		return true;
	}

	@Override
	public Boolean clearDatabase() {
		employeeRepository.deleteAll();
		return true;
	}


}

