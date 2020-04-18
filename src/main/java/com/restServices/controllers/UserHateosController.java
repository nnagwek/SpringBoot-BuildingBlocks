package com.restServices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restServices.Exceptions.UserNotFoundException;
import com.restServices.Exceptions.UsernameNotFoundException;
import com.restServices.entities.Order;
import com.restServices.entities.User;
import com.restServices.services.UserService;

@RestController
@Validated
@RequestMapping("/Hateos/users")
public class UserHateosController {

	@Autowired
	private UserService userService;

	
	@GetMapping
	public CollectionModel<User> getAllUsers() {
		List<User> allUsers =  userService.getAllUsers();
		for (User user : allUsers) {
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			
			//Relationship link with getAllOrders
			CollectionModel<Order> orders = null;
			try {
				orders = WebMvcLinkBuilder.methodOn(OrderHateosController.class).getAllOrders(userId);
//				orders = ControllerLinkBuilder.methodOn(OrderHateosController.class).getAllOrders(userId);
			} catch (UsernameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Link orderslink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderslink);
			
		}
		//Self link for getAllUsers
//		Link selflinkgetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Link selflinkgetAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalResources = new CollectionModel<User>(allUsers, selflinkgetAllUsers);
		return finalResources; 
	}

	// getUserById
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(2) Long id) {
		try {
			Optional<User> userOptional =  userService.getUserById(id);
			User user = userOptional.get();
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
//			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			
			user.add(selfLink);
			
			EntityModel<User> userEntityModel = new EntityModel<User>(user, selfLink);
			return userEntityModel;
			
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}

