package com.shani.car_rental_api_spring_boot_project.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Role;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;
import com.shani.car_rental_api_spring_boot_project.service.CarOwnerService;
import com.shani.car_rental_api_spring_boot_project.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final CarOwnerService carOwnerService;
	
	private final RoleService roleService;
	
	private static final Logger LOGGER =org.slf4j.LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/registerCarOwner")
	public ResponseEntity<CarOwnerResponseDto> registerCarOwner(@RequestBody CarOwnerRequestDto dto){
		
		CarOwnerResponseDto responseDto=carOwnerService.registerCarOwner(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
		
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
	@PostMapping("/saveRole")
	public Role saveRoleService(@RequestBody Role role) {
		return roleService.saveRole(role);
	}
}
