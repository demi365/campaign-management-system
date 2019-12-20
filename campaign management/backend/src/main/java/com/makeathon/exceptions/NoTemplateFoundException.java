package com.makeathon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTemplateFoundException extends RuntimeException {

	 /**
	 * default serial value generated
	 */
	private static final long serialVersionUID = -8154519873677927268L;

	public NoTemplateFoundException(String exception) {
	    super(exception);
	  }
}
