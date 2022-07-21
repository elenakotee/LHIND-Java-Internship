package com.springbootrest.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "employee_details")
public class EmployeeDetails {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "phone_number", length = 30)
	private String phoneNumber;

	@Column(name = "employment_date")
	@Temporal(value = TemporalType.DATE)
	private Date employmentDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;

	public EmployeeDetails() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeDetails{" +
				"id=" + id +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", employmentDate=" + employmentDate +
				", employee=" + employee +
				'}';
	}
}

