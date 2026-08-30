package com.shani.car_rental_api_spring_boot_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shani.car_rental_api_spring_boot_project.dto.CarRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toCar(CarRequestDto carRequestDto);

    @Mapping(source = "carOwner.id", target = "carOwnerId")
    CarResponseDto toCarResponseDto(Car car);
}