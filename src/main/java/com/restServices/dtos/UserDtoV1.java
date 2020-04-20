package com.restServices.dtos;

import java.util.List;

import com.restServices.entities.Order;

public class UserDtoV1 {

	
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	private String ssn;
	private List<Order> order;
	
	public UserDtoV1() {
	}
	
	public UserDtoV1(Long id, String username, String firstname, String lastname, String email, String role, String ssn,
			List<Order> order) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.order = order;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "UserDtoV1 [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", order=" + order + "]";
	}
	
	
}
