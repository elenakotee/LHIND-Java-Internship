package com.springbootrest;

import com.springbootrest.model.entity.Employee;
import com.springbootrest.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmployeeService service) {

		return (args -> {
			service.clearDatabase();
			insertEmployees(service);
			System.out.println("........................................................");
			System.out.println(service.retrieveAllEmployees(1, 10));
			System.out.println("........................................................");

			System.out.println("........................................................");
			System.out.println(service.retrieveEmployeesByFirstName("Elena"));
			System.out.println("........................................................");
		});
	}

	private void insertEmployees(EmployeeService service) {
		Employee e1 = new Employee("Elena", "Kote");
		service.storeEmployee(e1);
		service.storeEmployee(new Employee("Aa", "Bb"));
		service.storeEmployee(new Employee("Cc", "Dd"));
		service.storeEmployee(new Employee("Elena", "Dy"));
		service.storeEmployee(new Employee("Elena", "Tre"));

	}


}
