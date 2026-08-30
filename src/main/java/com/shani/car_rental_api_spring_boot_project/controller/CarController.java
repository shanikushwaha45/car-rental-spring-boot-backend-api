package com.shani.car_rental_api_spring_boot_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shani.car_rental_api_spring_boot_project.dto.CarRequestDto;

import com.shani.car_rental_api_spring_boot_project.service.CarService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carController")
@RequiredArgsConstructor
public class CarController {
	
	private final CarService carService;
	
	private final HttpSession httpSession;
	
	@PostMapping("/registerCar")
	public ResponseEntity<?> registerCar(@Valid @RequestBody CarRequestDto requestDto,HttpSession httpSession) {
		return carService.registerCar(requestDto, httpSession);
	}
	
	

}
