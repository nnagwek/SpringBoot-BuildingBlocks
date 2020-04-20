package com.restServices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restServices.Exceptions.UserNotFoundException;
import com.restServices.dtos.UserMmDTO;
import com.restServices.entities.User;
import com.restServices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// getUserById
	@GetMapping("/{id}")
	public UserMmDTO getUserById(@PathVariable("id") @Min(2) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		User user = userOptional.get();

		UserMmDTO userMmDTO = modelMapper.map(user, UserMmDTO.class);
		return userMmDTO;
	}

}
