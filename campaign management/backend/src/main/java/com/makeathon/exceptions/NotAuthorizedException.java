package com.makeathon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAuthorizedException extends RuntimeException{

	/**
	 * default serial key generated
	 */
	private static final long serialVersionUID = -8610026872446590071L;

	private static String exception = "Authorization code invalid";
	
	public NotAuthorizedException(String exception) {
		super(exception);
	}

	public NotAuthorizedException() {
		super(exception);
	}
}
