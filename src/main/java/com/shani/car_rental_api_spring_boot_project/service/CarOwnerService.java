package com.shani.car_rental_api_spring_boot_project.service;

import java.util.Locale;



import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.entity.Role;
import com.shani.car_rental_api_spring_boot_project.exception.RoleNotFoundException;
import com.shani.car_rental_api_spring_boot_project.mapper.CarOwnerMapper;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;
import com.shani.car_rental_api_spring_boot_project.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarOwnerService {
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(CarOwnerService.class);
	private final CarOwnerRepository carOwnerRepository;
	private final PasswordEncoder passwordEncoder;
	private final CarOwnerMapper carOwnerMapper;
	private final RoleRepository roleRepository;
	
	
	@Transactional
	public CarOwnerResponseDto registerCarOwner(CarOwnerRequestDto carOwnerRequestDto) {
		LOGGER.info("RegisterCarOwner execution started");
		
		if(carOwnerRepository.existsByEmail(carOwnerRequestDto.getEmail().trim().toLowerCase(Locale.ROOT))) {
			throw new RuntimeException("Email Already Exists");
		}
		
		Role role=roleRepository.findByName("Role_CarOwner").orElseThrow(()->new RoleNotFoundException("Role is not available"));
		
		carOwnerRequestDto.setPassword(passwordEncoder.encode(carOwnerRequestDto.getPassword()));
		CarOwner carOwner=carOwnerMapper.toCarOwner(carOwnerRequestDto);
		CarOwner dabCarOwner=carOwnerRepository.saveAndFlush(carOwner);
		
		return carOwnerMapper.toCarOwnerResponseDto(dabCarOwner);
	}
	
	public CarOwner getCarOwnerByEmail(String email) {
		return carOwnerRepository.findByEmail(email).get();
	}
}
