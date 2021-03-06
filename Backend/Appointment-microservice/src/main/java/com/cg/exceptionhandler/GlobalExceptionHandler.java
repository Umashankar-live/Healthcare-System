package com.cg.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoValueFoundException.class)
	public ResponseEntity<Object> handlingNoValueFoundException(NoValueFoundException e, WebRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiError error = new ApiError(e.getMessage(),status);
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(value = NotPossibleException.class)
	public ResponseEntity<Object> handlingNotPossibleException(NotPossibleException e, WebRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiError error = new ApiError(e.getMessage(),status);
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException e, WebRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiError error = new ApiError(e.getLocalizedMessage(),status);
		return new ResponseEntity<>(error, status);
	}
}
