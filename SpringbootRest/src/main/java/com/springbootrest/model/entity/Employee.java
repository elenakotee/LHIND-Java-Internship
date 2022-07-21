package com.springbootrest.model.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@OneToOne(mappedBy = "employee", fetch = FetchType.EAGER)
	private EmployeeDetails employeeDetails;

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<Booking> bookings;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<com.springbootrest.model.entity.Role> roles;

	public Employee() {
	}

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				", employeeDetails=" + employeeDetails +
				", bookings=" + bookings +
				", roles=" + roles +
				'}';
	}
}
