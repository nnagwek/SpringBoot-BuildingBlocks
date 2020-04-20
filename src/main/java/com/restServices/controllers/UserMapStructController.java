package com.restServices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.dtos.UserMsDTO;
import com.restServices.mappers.UserMapper;
import com.restServices.respositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserMsDTO> getAllUsers(){
		return userMapper.usersToUsersDtos(userRepository.findAll());
	}

}
