package com.hungernet2.model.requestentities;
import com.hungernet2.model.entity.*;

import java.util.Set;

public class RequestUser {

	private String username;

	private String userPassword;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
