package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ValidationExceptions extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public ValidationExceptions(String message){
    	super(message); 
}
}
