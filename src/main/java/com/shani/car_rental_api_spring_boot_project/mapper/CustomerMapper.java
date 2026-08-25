package com.shani.car_rental_api_spring_boot_project.mapper;

import org.mapstruct.Mapper;

import com.shani.car_rental_api_spring_boot_project.dto.CustomerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CustomerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	Customer toCustomer(CustomerRequestDto customerRequestDto);
	
	CustomerResponseDto toCustomerResponseDto(Customer customer);
}
