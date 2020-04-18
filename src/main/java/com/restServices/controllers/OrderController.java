package com.restServices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restServices.Exceptions.OrderNotFoundException;
import com.restServices.Exceptions.UsernameNotFoundException;
import com.restServices.entities.Order;
import com.restServices.entities.User;
import com.restServices.respositories.OrderRepository;
import com.restServices.respositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository OrderRepository;
	
	// Get All Orders
	@GetMapping("/{userId}/orders")
	private List<Order> getAllOrders(@PathVariable Long userId) throws UsernameNotFoundException{
		
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User not found in User Repository");
		}
		
		return user.get().getOrder();	
	}
	
	// Create order
	@PostMapping("/{userId}/orders")
	private Order createOrder(@PathVariable Long userId ,@RequestBody Order order) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UsernameNotFoundException("User not found in User Repository");
		}
		User user = userOptional.get();
		order.setUser(user);
		return OrderRepository.save(order);	
	}
	
	// Get Order by order Id
	@GetMapping("/{userId}/orders/{orderId}")
	private Order getOrderbyOrderId(@PathVariable Long userId , @PathVariable Long orderId ) throws UsernameNotFoundException,OrderNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userId);
		Optional<Order> orderOptional = OrderRepository.findById(orderId);
		if(!userOptional.isPresent()) {
			throw new UsernameNotFoundException("User not found in User Repository");
		}
		else if(userOptional.isPresent() && !orderOptional.isPresent()) {
			throw new OrderNotFoundException("User found but order of that user not found in User Repository");
		}
		Order orderPresent = null ;
		User user = userOptional.get();
		List<Order> order = user.getOrder();
		 for (Order orderById : order) {
			if ((orderById.getOrderId()).equals(orderId)) {
				orderPresent = orderById;
			}
		}
		if (null == orderPresent  ) {
			throw new OrderNotFoundException("User found but order doesn't belong to the user");
		} 
		return orderPresent;
	}
	
	
	
}
