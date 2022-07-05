package repository;

import model.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Integer> {

    List<Employee> findByFirstName(String firstName);

}
