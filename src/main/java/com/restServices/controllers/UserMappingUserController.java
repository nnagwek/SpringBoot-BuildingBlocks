package com.restServices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restServices.Exceptions.UserNotFoundException;
import com.restServices.entities.User;
import com.restServices.services.UserService;

@RestController
@RequestMapping("/jacksonfilter/users")
public class UserMappingUserController {

	@Autowired
	private UserService userService;

	// getUserById
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(2) Long id) {
		try {
			Optional<User> userOptional =  userService.getUserById(id);
			User user = userOptional.get();
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("order");
			
			FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue  mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filters);
			return mapper;
			
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	// getUserById - fields with RequestParam
		@GetMapping("/params/{id}")
		public MappingJacksonValue getUserById2(@PathVariable("id") @Min(2) Long id, @RequestParam Set<String> fields) {
			try {
				Optional<User> userOptional =  userService.getUserById(id);
				User user = userOptional.get();
				
				FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
				MappingJacksonValue  mapper = new MappingJacksonValue(user);
				
				mapper.setFilters(filters);
				return mapper;
				
				
			} catch (UserNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
		}

}
