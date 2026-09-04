package com.shani.car_rental_api_spring_boot_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shani.car_rental_api_spring_boot_project.dto.BookingRequestDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final com.shani.car_rental_api_spring_boot_project.service.CarService carService;
	
	private final HttpSession httpSession;
	
	private final com.shani.car_rental_api_spring_boot_project.service.BookingService bookingService;
	
	@GetMapping("/getAllCars")
	public ResponseEntity<?> getAllCarsService() {
		
		String email = (String) httpSession.getAttribute("customerSession");
		
		if(email == null) {
			
			return ResponseEntity.ok().body("you are not logged in please login and then try");
			
		}
		
		return ResponseEntity.status(HttpStatus.FOUND).body(carService.getAllCars());
	}
	
	@PostMapping("/bookCar/{carId}")
	public ResponseEntity<?> bookCarService(@PathVariable Long carId,@RequestBody BookingRequestDto bookingRequestDTO) {

		return bookingService.bookCarService(carId, httpSession, bookingRequestDTO);
	}
		
}