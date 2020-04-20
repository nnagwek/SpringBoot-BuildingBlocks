package com.restServices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.Exceptions.UserNotFoundException;
import com.restServices.dtos.UserDtoV1;
import com.restServices.dtos.UserDtoV2;
import com.restServices.entities.User;
import com.restServices.services.UserService;

@RestController
@RequestMapping("/versioning/header/users")
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// Custom Header based Versioning - V1
	// getUserById
	@GetMapping(value = "{id}", headers = "API-VERSION=1")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(2) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		User user = userOptional.get();

		UserDtoV1 userDTOV1 = modelMapper.map(user, UserDtoV1.class);
		return userDTOV1;
	}

	// Custom Header based Versioning - V2
	// getUserById
	@GetMapping(value = "{id}", headers = "API-VERSION=2")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(2) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		User user = userOptional.get();

		UserDtoV2 userDTOV2 = modelMapper.map(user, UserDtoV2.class);
		return userDTOV2;
	}

}
