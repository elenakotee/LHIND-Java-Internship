package repository.impl;

import configuration.EntityManagerConfiguration;
import model.entity.Booking;
import model.entity.Employee;
import repository.EmployeeRepository;
import util.Queries;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager = EntityManagerConfiguration.getEntityManager();

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> result =
                entityManager.createQuery(Queries.FIND_ALL_EMPLOYEES, Employee.class);
        return result.getResultList();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        TypedQuery<Employee> result =
                entityManager.createQuery(Queries.FIND_EMPLOYEE_BY_FIRST_NAME_METHOD_2, Employee.class);
        result.setParameter("firstName", firstName);
        return result.getResultList();    }

    @Override
    public Integer update(Employee employee) {
        entityManager.getTransaction().begin();
        Employee newemp = entityManager.find(Employee.class, employee.getId());

        if(newemp != null) {
            newemp.setEmployeeDetails(employee.getEmployeeDetails());
            newemp.setFirstName(employee.getFirstName());
            newemp.setLastName(employee.getLastName());
            newemp.setMiddleName(employee.getMiddleName());
            newemp.setBookings(employee.getBookings());
            newemp.setRoles(employee.getRoles());
            entityManager.getTransaction().commit();
            entityManager.close();
            return 1;
        }
        return 0;
    }

    @Override
    public void remove(Integer id) {
        entityManager.getTransaction().begin();
       Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
