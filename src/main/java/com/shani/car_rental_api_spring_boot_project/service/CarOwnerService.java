package com.shani.car_rental_api_spring_boot_project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarOwnerService {
	private final CarOwnerRepository carOwnerRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public CarOwnerResponseDto registerCarOwner(CarOwnerRequestDto carOwnerRequestDto) {
		if(carOwnerRepository.existsByEmail(carOwnerRequestDto.getEmail())) {
			throw new RuntimeException("Email Already Exists");
		}
		carOwnerRequestDto.setPassword(passwordEncoder.encode(carOwnerRequestDto.getPassword()));
		
		
		return null;
	}
	
	public CarOwner getCarOwnerByEmail(String email) {
		return carOwnerRepository.findByEmail(email).get();
	}
}
