package service;

import model.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	void saveEmployee(Employee employee);
}