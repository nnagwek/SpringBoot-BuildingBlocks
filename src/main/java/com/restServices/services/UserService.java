package com.restServices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.restServices.entities.User;
import com.restServices.respositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// getAllUsers method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// createUser Method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	// getUserById
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	// update by ID
	public User updateById(Long id , User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	//Delete By Id
	public void deleteById(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	// Find by Username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
