package com.springbootrest.model.builder;

import com.springbootrest.model.dto.EmployeeDTO;

public class EmployeeDTOBuilder {

	private EmployeeDTO employeeDTO = new EmployeeDTO();

	public EmployeeDTOBuilder withId(Integer id) {
		employeeDTO.setId(id);
		return this;
	}


	public EmployeeDTOBuilder withFirstName(String firstName) {
		employeeDTO.setFirstName(firstName);
		return this;
	}

	public EmployeeDTOBuilder withLastName(String lastName) {
		employeeDTO.setLastName(lastName);
		return this;
	}

	public EmployeeDTO build() {
		return employeeDTO;
	}
}
