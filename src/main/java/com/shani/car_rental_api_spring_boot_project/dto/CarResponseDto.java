package com.shani.car_rental_api_spring_boot_project.dto;



import lombok.Data;

@Data
public class CarResponseDto {

    private Long id;
    
    
	private String vehicleNumber;

    private String brand;

    private String fuelType;

    private String model;

    private Integer seatingCapacity;

    private Double pricePerDay;

    private Double pricePerKm;
    
    private String pinCode;

    private Long carOwnerId;
}