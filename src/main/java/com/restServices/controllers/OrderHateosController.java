package com.restServices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.Exceptions.UsernameNotFoundException;
import com.restServices.entities.Order;
import com.restServices.entities.User;
import com.restServices.respositories.UserRepository;

@RestController
@RequestMapping("/Hateos/users")
public class OrderHateosController {

	@Autowired
	private UserRepository userRepository;

	// Get All Orders
	@GetMapping("/{userId}/orders") 
	CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found in User Repository");
		}	
		
		List<Order> orders =  user.get().getOrder();
		CollectionModel<Order> orderCollectionModel = new CollectionModel<>(orders);
		return orderCollectionModel;
	}
}
