package com.parkingsystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SlotNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1098271777103147214L;

	public SlotNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
