package com.shani.car_rental_api_spring_boot_project.exception;

public class RoleNotFoundException extends RuntimeException{

	public RoleNotFoundException(String msg) {
		super(msg);
	}
}
