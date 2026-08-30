package com.shani.car_rental_api_spring_boot_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.service.CarOwnerService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/carOwner")
@RequiredArgsConstructor
public class CarOwnerController {
	
	private final HttpSession httpSession;
	
	private final CarOwnerService carOwnerService;
	
	@GetMapping("/logoutCarOwner")
	public ResponseEntity<String> logoutCarOwner(){
		String email=(String) httpSession.getAttribute("carOwnerSession");
		if(email==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("alredy logout");
		}
		httpSession.invalidate();
		return ResponseEntity.ok("logged out success");
	}
	@GetMapping("/profile")
	public ResponseEntity<?> viewProfile(){
		String email=(String) httpSession.getAttribute("carOwnerSession");
		if(email==null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not log in");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(carOwnerService.getCarOwnerByEmail(email));
	}

}
