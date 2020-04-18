package com.restServices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//RestController
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	
	@GetMapping("/hello-int")
	public String getMessageIN18Format(@RequestHeader(name = "Accept-Language",required = false) String locale) {
		
		return messageSource.getMessage( "label.hello",null, new Locale(locale));
		
	}
	
	@GetMapping("/hello-int2")
	public String getMessageIN18Format2( ) {
		
		return messageSource.getMessage( "label.hello",null , LocaleContextHolder.getLocale());
		
	}
}
