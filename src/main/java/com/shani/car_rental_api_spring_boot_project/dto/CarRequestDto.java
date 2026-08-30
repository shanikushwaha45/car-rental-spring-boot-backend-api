package com.shani.car_rental_api_spring_boot_project.dto;




import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CarRequestDto {
	
	@NotBlank(message = "Vehicle number is required")
	private String vehicleNumber;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Fuel type is required")
    private String fuelType;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Seating capacity is required")
    @Min(value = 1, message = "Seating capacity must be at least 1")
    private Integer seatingCapacity;

    @NotNull(message = "Price per day is required")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Price per day must be greater than 0"
    )
    private Double pricePerDay;

    @NotNull(message = "Price per km is required")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Price per km must be greater than 0"
    )
    private Double pricePerKm;
    
   
    @NotBlank(message = "Pin code is required")
    @Pattern(
        regexp = "^[1-9][0-9]{5}$",
        message = "Please enter a valid 6-digit Indian PIN code"
    )
    private String pinCode;

   
}