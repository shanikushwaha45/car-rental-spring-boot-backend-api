package com.shani.car_rental_api_spring_boot_project.exception;

@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException{

	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
