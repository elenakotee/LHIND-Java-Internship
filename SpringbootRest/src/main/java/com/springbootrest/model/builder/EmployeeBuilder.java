package com.springbootrest.model.builder;

import com.springbootrest.model.entity.Employee;

public class EmployeeBuilder{

	private Employee employee = new Employee();

	public Employee build() {
		return employee;
	}

	public EmployeeBuilder withId(Integer id) {
		employee.setId(id);
		return this;
	}

	public EmployeeBuilder withFirstName(String firstName) {
		employee.setFirstName(firstName);
		return this;
	}

	public EmployeeBuilder withLastName(String lastName) {
		employee.setLastName(lastName);
		return this;
	}

}
