package com.demospringsecurity.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
//add when configuring roles
/*@Setter
@Getter*/
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String authority;


	//do when configuring roles
	/*@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;*/



}
