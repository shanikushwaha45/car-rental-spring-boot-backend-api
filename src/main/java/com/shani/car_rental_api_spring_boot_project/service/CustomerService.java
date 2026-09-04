package com.shani.car_rental_api_spring_boot_project.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.CustomerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CustomerResponseDto;
import com.shani.car_rental_api_spring_boot_project.dto.LogInRequestDto;
import com.shani.car_rental_api_spring_boot_project.entity.Customer;
import com.shani.car_rental_api_spring_boot_project.entity.Role;
import com.shani.car_rental_api_spring_boot_project.exception.EmailAlreadyExistsException;
import com.shani.car_rental_api_spring_boot_project.exception.InvalidEmailException;
import com.shani.car_rental_api_spring_boot_project.exception.InvalidPasswordException;
import com.shani.car_rental_api_spring_boot_project.mapper.CustomerMapper;
import com.shani.car_rental_api_spring_boot_project.repository.CustomerRepository;
import com.shani.car_rental_api_spring_boot_project.repository.RoleRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	private final RoleRepository roleRepository;
	
	private final CustomerMapper customerMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	public ResponseEntity<CustomerResponseDto> registerCustomer(CustomerRequestDto requestDTO){
		
		String email = requestDTO.getEmail().trim().toLowerCase();
		
		if(customerRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException("this username is already exist change your email and then register");
		}
		
		Role role=roleRepository.findByName("Role_Customer").orElseThrow(()->new RuntimeException("role is not found"));
		
		Customer customer=customerMapper.toCustomer(requestDTO);
		
		customer.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
		
		customer.setRoles(List.of(role));
		
		Customer savedCustomer=customerRepository.save(customer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toCustomerResponseDto(savedCustomer));
	}
	
	public ResponseEntity<String> loginCustomer(LogInRequestDto requestDTO,HttpSession httpSession){
		
		String email = requestDTO.getEmail().trim().toLowerCase();
		
		Customer customer=customerRepository.findByEmail(email).orElseThrow(()->new InvalidEmailException("customer email is incorrect"));
	
		if (!passwordEncoder.matches(requestDTO.getPassword(), customer.getPassword())) {
			throw new InvalidPasswordException("invalid password");
		}
		
		httpSession.setAttribute("customerSession", customer.getEmail());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("customer login successfully");
	}
}
