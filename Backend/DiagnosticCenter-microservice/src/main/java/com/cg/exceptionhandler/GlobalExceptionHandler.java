package com.cg.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.exception.NoValueFoundException;
import com.cg.exception.NotPossibleException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoValueFoundException.class)
	public ResponseEntity<ApiError> handlingNoValueFoundException(NoValueFoundException e) {
		ApiError error = new ApiError();
		error.setException(" " + e.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(value = NotPossibleException.class)
	public ResponseEntity<ApiError> handlingNotPossibleException(NotPossibleException e) {
		ApiError error = new ApiError();
		error.setException(" " + e.getMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException e) {
		ApiError error = new ApiError();
		error.setException(" " + e.getLocalizedMessage());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(error, status);
	}
}
