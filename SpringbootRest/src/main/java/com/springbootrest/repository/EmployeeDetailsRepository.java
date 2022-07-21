package com.springbootrest.repository;

import com.springbootrest.model.entity.Employee;
import com.springbootrest.model.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Integer> {

	EmployeeDetails findByEmail(String email);

	EmployeeDetails findByPhoneNumber(String phoneNumber);

	EmployeeDetails findByEmployee(Employee employee);

}

