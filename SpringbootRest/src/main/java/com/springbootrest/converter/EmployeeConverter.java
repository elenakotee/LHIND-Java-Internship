package com.springbootrest.converter;

import com.springbootrest.model.builder.EmployeeBuilder;
import com.springbootrest.model.builder.EmployeeDTOBuilder;
import com.springbootrest.model.dto.EmployeeDTO;
import com.springbootrest.model.entity.Employee;

public class EmployeeConverter extends AbstractConverter<Employee, EmployeeDTO>{

	@Override
	public Employee toEntity(EmployeeDTO employeeDTO) {
		return new EmployeeBuilder()
				.withId(employeeDTO.getId())
				.withFirstName(employeeDTO.getFirstName())
				.withLastName(employeeDTO.getLastName())
				.build();
	}


	@Override
	public EmployeeDTO toDTO(Employee employee) {
		return new EmployeeDTOBuilder().
				withId(employee.getId())
				.withFirstName(employee.getFirstName())
				.withLastName(employee.getLastName())
				.build();
	}
}
