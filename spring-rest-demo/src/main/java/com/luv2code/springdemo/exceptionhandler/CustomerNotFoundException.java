package com.luv2code.springdemo.exceptionhandler;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message) {
		super(message);
		
	}
	
}
