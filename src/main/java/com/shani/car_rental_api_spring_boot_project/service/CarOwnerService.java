package com.shani.car_rental_api_spring_boot_project.service;

import java.util.Locale;



import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.dto.LogInRequestDto;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.entity.Role;
import com.shani.car_rental_api_spring_boot_project.exception.EmailAlreadyExistsException;
import com.shani.car_rental_api_spring_boot_project.exception.InvalidEmailException;
import com.shani.car_rental_api_spring_boot_project.exception.InvalidPasswordException;
import com.shani.car_rental_api_spring_boot_project.exception.RoleNotFoundException;
import com.shani.car_rental_api_spring_boot_project.mapper.CarOwnerMapper;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;
import com.shani.car_rental_api_spring_boot_project.repository.RoleRepository;

import jakarta.servlet.http.HttpSession;
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
			throw new EmailAlreadyExistsException("Email Already Exists");
		}
		
		Role role=roleRepository.findByName("Role_CarOwner").orElseThrow(()->new RoleNotFoundException("Role is not available"));
		
		carOwnerRequestDto.setPassword(passwordEncoder.encode(carOwnerRequestDto.getPassword()));
		CarOwner carOwner=carOwnerMapper.toCarOwner(carOwnerRequestDto);
		CarOwner dabCarOwner=carOwnerRepository.saveAndFlush(carOwner);
		
		return carOwnerMapper.toCarOwnerResponseDto(dabCarOwner);
	}
	
	
	public ResponseEntity<?> loginCarOwnerService(LogInRequestDto requestDto,HttpSession httpSession){
		LOGGER.info("Logine method execution Strated Login is under process");
		
		String email=requestDto.getEmail().trim().toLowerCase();
		
		CarOwner carOwner=carOwnerRepository.findByEmail(email).orElseThrow(()->new InvalidEmailException(email+": is invalid"));
		
		if(!passwordEncoder.matches(requestDto.getPassword(), carOwner.getPassword()))
			throw new InvalidPasswordException("invalid password");
		
		httpSession.setAttribute("carOwnerSession", carOwner.getEmail());
		
		LOGGER.info("login success");
		
		return ResponseEntity.ok("login successfull");
	}
	
	
	
	public CarOwnerResponseDto getCarOwnerByEmail(String email) {
		return carOwnerMapper.toCarOwnerResponseDto(carOwnerRepository.findByEmail(email).get());
		
	}
}
