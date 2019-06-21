package com.luv2code.springdemo.exceptionhandler;

public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException(String message) {
		super(message);
	}

	
}
