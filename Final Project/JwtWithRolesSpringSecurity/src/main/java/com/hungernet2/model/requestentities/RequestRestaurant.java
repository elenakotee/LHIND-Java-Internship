package com.hungernet2.model.requestentities;

public class RequestRestaurant {

	private Integer id;

	private String name;

	private String managerUsername;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	@Override
	public String toString() {
		return "RequestRestaurant{" +
				"id=" + id +
				", name='" + name + '\'' +
				", managerUsername='" + managerUsername + '\'' +
				'}';
	}
}