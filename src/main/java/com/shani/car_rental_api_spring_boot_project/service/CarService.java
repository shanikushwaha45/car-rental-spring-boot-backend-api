package com.shani.car_rental_api_spring_boot_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.CarRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Car;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.exception.CarNotFoundException;
import com.shani.car_rental_api_spring_boot_project.mapper.CarMapper;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;
import com.shani.car_rental_api_spring_boot_project.repository.CarRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
	
	
	private final CarOwnerRepository carOwnerRepository;
	
	private final CarRepository carRepository;
	private final CarMapper carMapper;
	
	public ResponseEntity<?> registerCar(CarRequestDto requestDto, HttpSession httpSession) {

	    String email = (String) httpSession.getAttribute("carOwnerSession");
	    System.out.println("Email is: "+email);

	    if (email == null) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("Log in first to add the Car");
	    }

	    CarOwner carOwner = carOwnerRepository.findByEmail(email).get();

	    System.out.println("Email = " + email);
	    System.out.println("CarOwner = " + carOwner);
	    System.out.println("CarOwner ID = " + carOwner.getId());

	    Car car = carMapper.toCar(requestDto);

	    car.setCarOwner(carOwner);

	    System.out.println("Car carOwner = " + car.getCarOwner());
	    System.out.println("Car carOwner ID = " + car.getCarOwner().getId());

	    Car savedCar = carRepository.save(car);

	    System.out.println("Saved Car owner = " + savedCar.getCarOwner());
	    System.out.println("Saved Car owner ID = " + savedCar.getCarOwner().getId());

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(carMapper.toCarResponseDto(savedCar));
	}
	
	
	public CarResponseDto getCarByVehicleNumber(String vehicleNumber) {

	    Car car = carRepository.findByVehicleNumber(vehicleNumber)
	            .orElseThrow(() -> new CarNotFoundException("Car not found"));

	    return carMapper.toCarResponseDto(car);
	}
	
	public List<CarResponseDto> getAllCars(){
		List<Car> cars=carRepository.findAll();
		List<CarResponseDto> dtos=new ArrayList<>();
		for(Car car:cars) {
			dtos.add(carMapper.toCarResponseDto(car));
		}
		return dtos;
	}
	
	public List<CarResponseDto> getAllCarsByPinCode(String pin){
		 List<Car> cars=carRepository.findByPinCode(pin);
		 
		 List<CarResponseDto> dtos=new ArrayList<>();
		 
		 for(Car car:cars) {
			 dtos.add(carMapper.toCarResponseDto(car));
		 }
		 return dtos;
	}
	public Car getCarByIdService(Long carId) {

		return carRepository.findById(carId)
				.orElseThrow(() -> new RuntimeException("Car with id " + carId + " not found"));

	}

}
