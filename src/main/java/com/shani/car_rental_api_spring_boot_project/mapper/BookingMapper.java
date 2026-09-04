package com.shani.car_rental_api_spring_boot_project.mapper;

import org.mapstruct.Mapper;

import com.shani.car_rental_api_spring_boot_project.dto.BookingResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Booking;



@Mapper(componentModel = "spring")
public interface BookingMapper {

	BookingResponseDto toBookingResponseDTO(Booking booking);
}