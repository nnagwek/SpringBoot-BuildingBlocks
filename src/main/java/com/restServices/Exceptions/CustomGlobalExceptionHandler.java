package com.restServices.Exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValidException in Global Exception handler", ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetailsMethodNotSupported = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in Global Exception handler", ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetailsMethodNotSupported, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
			 WebRequest request) {

		CustomErrorDetails customErrorDetailsMethodNotSupported = new CustomErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(customErrorDetailsMethodNotSupported, HttpStatus.METHOD_NOT_ALLOWED);
	}

	//	ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		
		
		CustomErrorDetails customErrorDetailsMethodNotSupported = new CustomErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(customErrorDetailsMethodNotSupported, HttpStatus.BAD_REQUEST);
	}
}
