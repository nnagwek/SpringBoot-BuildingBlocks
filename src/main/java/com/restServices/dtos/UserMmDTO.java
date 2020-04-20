package com.restServices.dtos;

import java.util.List;

import com.restServices.entities.Order;

public class UserMmDTO {

	private Long userId;
	
	private String username;

	private String firstname;
	
	private List<Order> order;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
