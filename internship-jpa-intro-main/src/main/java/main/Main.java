package main;

import model.entity.Employee;
import model.entity.Role;
import model.entity.RoleEnum;
import repository.EmployeeRepository;
import repository.impl.EmployeeRepositoryImpl;

public class Main {

    public static void main(String[] args) {

        Employee e1 = new Employee();
        e1.setFirstName("Elena");
        e1.setLastName("Kote");

        Role role = new Role();
        role.setRole(RoleEnum.EMPLOYEE);
        role.setDescription("hjh");
        e1.getRoles().add(role);

        EmployeeRepositoryImpl empRepo = new EmployeeRepositoryImpl();
        empRepo.save(e1);




    }

}
