package com.demospringsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Data
//add when configuring roles
/*
@Setter
@Getter
*/
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	@JsonIgnore
	//ignores the password when its sent back to the client, it's not shown on postman when u register a user
	private String password;


	//use when configuring authorities
	@OneToOne
	@JoinColumn(name = "authority_id")
	private Authority authority;

	/*//use when configuring user roles, one user can have multiple roles/authorities
	@OneToOne(mappedBy = "user")
	private Set<Authority> authorities;*/


}
