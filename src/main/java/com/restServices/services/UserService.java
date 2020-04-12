package com.restServices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.restServices.Exceptions.UserExistsException;
import com.restServices.Exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException{
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser != null ) {
			throw new UserExistsException("User already exists in the repository");
		}
		return userRepository.save(user);
	}
	
	// getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in User Repository");
		}
		return user;
	}
	
	// update by ID
	public User updateById(Long id , User user) throws UserNotFoundException {
		Optional<User> OptionalUser = userRepository.findById(id);
		
		if (!OptionalUser.isPresent()) {
			throw new UserNotFoundException("User not found please provide correct User Id");	
		}
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	//Delete By Id
	public void deleteById(Long id) throws UserNotFoundException  {
		
Optional<User> OptionalUser = userRepository.findById(id);
		
		if (!OptionalUser.isPresent()) {
			throw new UserNotFoundException("User not found please provide correct User Id to delete it.");	
		}
		
		userRepository.deleteById(id);
	}
	
	// Find by Username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
