package com.shani.car_rental_api_spring_boot_project.dto;

import lombok.Data;

@Data
public class CarOwnerResponseDto {

	private Long id;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private String licenseNumber;
}
