package com.springbootrest.service.impl;

import com.springbootrest.model.builder.EmployeeBuilder;
import com.springbootrest.model.dto.EmployeeDTO;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.repository.EmployeeRepository;
import com.springbootrest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceImplTest {

	private EmployeeRepository employeeRepository;
	private EmployeeService employeeService;

	@BeforeEach
	public void setUp() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		employeeService = new EmployeeServiceImpl(employeeRepository);
	}

	@Test
	public void test_with_page_number_1_and_page_size_3_should_return_3() {
		List<Employee> loadResult = Arrays.asList(new EmployeeBuilder().withId(4).build(),
				new EmployeeBuilder().withId(3).build(),
				new EmployeeBuilder().withId(2).build(),
				new EmployeeBuilder().withId(1).build());

		Mockito.when(employeeRepository.findAll()).thenReturn(loadResult);
		assertEquals(3, employeeService.retrieveAllEmployees(1, 3).size());
	}

	@Test
	public void test_with_random_employee_ids_should_sort_by_id() {

		List<Employee> loadResult = Arrays.asList(new EmployeeBuilder().withId(4).build(),
				new EmployeeBuilder().withId(3).build(),
				new EmployeeBuilder().withId(2).build(),
				new EmployeeBuilder().withId(1).build());
		Mockito.when(employeeRepository.findAll()).thenReturn(loadResult);
		List<EmployeeDTO> employees = employeeService.retrieveAllEmployees(1, 3);
		assertEquals(1, employees.get(0).getId());
	}


	@Test
	public void test_with_random_employeeIds_should_sort_by_id_() {
		List<Employee> loadResult = Arrays.asList(new EmployeeBuilder().withId(4).build(),
				new EmployeeBuilder().withId(3).build(),
				new EmployeeBuilder().withId(2).build(),
				new EmployeeBuilder().withId(1).build());
		Mockito.when(employeeRepository.findAll()).thenReturn(loadResult);
		//Mockito.doThrow(new SQLException()).when(employeeRepository).findAll();
		assertThrows(IndexOutOfBoundsException.class, () -> employeeService.retrieveAllEmployees(-1, 3));

	}
}
