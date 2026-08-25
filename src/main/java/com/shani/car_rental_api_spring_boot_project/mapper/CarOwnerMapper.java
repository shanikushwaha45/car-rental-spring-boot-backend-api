package com.shani.car_rental_api_spring_boot_project.mapper;

import org.mapstruct.Mapper;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;

@Mapper(componentModel = "spring")
public interface CarOwnerMapper {

	CarOwner toCarOwner(CarOwnerRequestDto carOwnerRequestDto);
	
	CarOwnerResponseDto toCarOwnerResponseDto(CarOwner carOwner);
}
