package com.shani.car_rental_api_spring_boot_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	
	@PostMapping("/registerCarOwner")
	public ResponseEntity<?> registerCarOwner(){
		return ResponseEntity.ok("Car owner Registere successfully");
	}
	@PostMapping("/registerCustomer")
	public ResponseEntity<?> registerCustomer(){
		return ResponseEntity.ok("Customer registered");
	}
	@GetMapping("/loginCustomer")
	public ResponseEntity<?> loginCustomer(){
		return ResponseEntity.ok("Customer logged in success");
	}
	@GetMapping("/loginCarOwner")
	public ResponseEntity<?> loginCarOwner(){
		return ResponseEntity.ok("Car owner logged in success");
	}
}
