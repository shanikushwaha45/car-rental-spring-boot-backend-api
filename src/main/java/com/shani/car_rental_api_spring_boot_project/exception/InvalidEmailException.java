package com.shani.car_rental_api_spring_boot_project.exception;

public class InvalidEmailException extends RuntimeException{

	public InvalidEmailException(String msg){
		super(msg);
	}
}
