package com.restServices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.entities.User;
import com.restServices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	
	//Update By Id
	@PutMapping("/users/{id}")
	public User updateById(@PathVariable("id") Long id , @RequestBody User user) {
		return userService.updateById(id, user);
	}
	
	// Delete by ID
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
	}
	
	// Find by Username
	@GetMapping("/users/byusername/{username}")
	public User findByUsername(@PathVariable("username")String username) {
		return userService.findByUsername(username);
				
	}
}
