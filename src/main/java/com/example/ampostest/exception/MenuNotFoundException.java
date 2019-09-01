package com.example.ampostest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MenuNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MenuNotFoundException(String message) {
		super(message);
	}
}
