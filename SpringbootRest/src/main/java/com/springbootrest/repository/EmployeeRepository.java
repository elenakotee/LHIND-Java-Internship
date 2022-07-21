package com.springbootrest.repository;

import com.springbootrest.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByLastName(String firstName);


}
