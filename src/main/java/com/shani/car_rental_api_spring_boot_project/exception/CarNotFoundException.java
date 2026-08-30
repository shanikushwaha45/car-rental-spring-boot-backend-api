package com.shani.car_rental_api_spring_boot_project.exception;

public class CarNotFoundException extends RuntimeException{
	
	
	public CarNotFoundException(String msg) {
		super(msg);
	}
	

}
