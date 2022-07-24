package com.demospringsecurity.entity;

import lombok.Data;

import java.util.Set;

@Data
public class UserModel {

	private String email;

	private String password;

	private Authority authority;

//	private Set<Authority> authorities;
}
