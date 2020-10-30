package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Custom Exception for scenarios where any value we are accessing is not present.
@ResponseStatus(HttpStatus.NOT_FOUND)   //404 Not Found
public class NoValueFoundException extends RuntimeException {

	public NoValueFoundException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
