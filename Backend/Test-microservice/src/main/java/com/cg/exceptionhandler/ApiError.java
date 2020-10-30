package com.cg.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiError {
	private String exception;
	private HttpStatus httpStatus;

	public ApiError(String exception, HttpStatus httpStatus) {
		super();
		this.exception = exception;
		this.httpStatus = httpStatus;
	}

	public ApiError() {
		super();
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
