package com.shani.car_rental_api_spring_boot_project.exception;

public class EmailAlreadyExistsException extends RuntimeException{
	
	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}

}
