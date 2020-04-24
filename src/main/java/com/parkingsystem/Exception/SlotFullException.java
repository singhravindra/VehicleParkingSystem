package com.parkingsystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SlotFullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844270970688850167L;

	public SlotFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
