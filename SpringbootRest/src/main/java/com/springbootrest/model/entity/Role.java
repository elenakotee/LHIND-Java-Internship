package com.springbootrest.model.entity;


import com.springbootrest.model.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "role", nullable = false)
	private RoleEnum role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<Employee> employees;

	@Column(name = "description")
	private String description;

	public Role() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", role=" + role +
				", employees=" + employees +
				", description='" + description + '\'' +
				'}';
	}
}
