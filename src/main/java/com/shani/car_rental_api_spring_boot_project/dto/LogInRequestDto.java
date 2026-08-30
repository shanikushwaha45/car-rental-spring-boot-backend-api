package com.shani.car_rental_api_spring_boot_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LogInRequestDto {

	@NotBlank(message="Email is required")
	@Email(message="Please provide a valid Email")
	private String email;
	@NotBlank(message = "please provide password")
	
	private String password;
}
