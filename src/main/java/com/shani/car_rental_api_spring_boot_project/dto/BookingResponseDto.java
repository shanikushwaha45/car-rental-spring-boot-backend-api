package com.shani.car_rental_api_spring_boot_project.dto;

import lombok.Data;

import com.shani.car_rental_api_spring_boot_project.entity.Booking;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class BookingResponseDto {

	private String message;
	private Booking booking;
	
	
}