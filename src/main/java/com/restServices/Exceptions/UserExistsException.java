package com.restServices.Exceptions;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = 4679037303350513944L;

	public UserExistsException(String message) {
		super(message);
	}

}
