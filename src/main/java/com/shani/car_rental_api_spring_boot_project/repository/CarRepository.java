package com.shani.car_rental_api_spring_boot_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shani.car_rental_api_spring_boot_project.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByVehicleNumber(String carNumber);

    List<Car> findByPinCode(String pinCode);
    
    
}