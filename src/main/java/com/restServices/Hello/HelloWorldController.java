package com.restServices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//RestController
@RestController
public class HelloWorldController {
	
	//Simple method
	//URI
	//GET
//	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/hello")
	public String getHelloWorld() {
		
		return " Hello World from HelloWorld Controller with Get Mapping";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails getUserDetails() {
		
		return new UserDetails("Niky", "Nagwekar", "Thane");
		
	}
	
}
