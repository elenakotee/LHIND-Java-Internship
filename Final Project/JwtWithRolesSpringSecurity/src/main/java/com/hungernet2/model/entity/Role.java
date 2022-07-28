package com.hungernet2.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer id;

	//can be either ADMIN, RESTAURANT_MANAGER, CLIENT
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String description;

	@ManyToMany(mappedBy = "roles")
	List<User> users;


	public Role() {
	}

	public Role(String roleName, String description) {
		this.roleName = roleName;
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}*/

	@Override
	public String toString() {
		return "Role{" +
				"roleName='" + roleName + '\'' +
				", description='" + description + '\'' +
				//", users=" + users +
				'}';
	}
}
