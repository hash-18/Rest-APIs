package com.mac.rest.restfulwebservices.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
// This makes sure that 404 is returned when we get UserNotFoundException
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
