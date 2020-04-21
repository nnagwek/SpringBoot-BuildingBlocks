package com.restServices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.net.MediaType;
import com.restServices.Exceptions.UserExistsException;
import com.restServices.Exceptions.UserNotFoundException;
import com.restServices.entities.User;
import com.restServices.services.UserService;

import ch.qos.logback.core.util.ContentTypeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "User Management RESTful Service" , value = "UserController", description = "Controller for user management restful service")
@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retrieve list  of users")
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@ApiOperation(value = "Creates a new User")
	@PostMapping
	public ResponseEntity<Void>  createUser(@ApiParam("User information for the new user to be created ")  @Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	// getUserById
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(2) Long id) {
		try {
			Optional<User> userOptional =  userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	// Update By Id
	@PutMapping("/{id}")
	public User updateById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateById(id, user);
		} catch (UserNotFoundException e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// Delete by ID
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		try {
			userService.deleteById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// Find by Username
	@GetMapping("/byusername/{username}")
	public User findByUsername(@PathVariable("username") String username) throws UserNotFoundException {
		
		User user = userService.findByUsername(username);
		
		if ( user == null) {
			throw new UserNotFoundException("Username '"+username+"' not found in the repository");
		}
		return user;

	}
}
