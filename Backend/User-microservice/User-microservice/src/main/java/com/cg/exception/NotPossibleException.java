package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//Custom Exception to throw an exception whenever anything that can't be done due to business logic is tried 
@ResponseStatus(HttpStatus.BAD_REQUEST)   //404 Not Found
public class NotPossibleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotPossibleException(String message) {
		super(message);
	}
}
