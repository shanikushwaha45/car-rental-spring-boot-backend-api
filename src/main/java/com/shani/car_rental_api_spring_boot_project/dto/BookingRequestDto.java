package com.shani.car_rental_api_spring_boot_project.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingRequestDto {

	private LocalDate journeyDate;

	private String source;

	private String destination;

}