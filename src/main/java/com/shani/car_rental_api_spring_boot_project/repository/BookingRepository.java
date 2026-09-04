package com.shani.car_rental_api_spring_boot_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shani.car_rental_api_spring_boot_project.entity.Booking;
import com.shani.car_rental_api_spring_boot_project.entity.Car;
import com.shani.car_rental_api_spring_boot_project.entity.Customer;
import com.shani.car_rental_api_spring_boot_project.enums.BookingStatus;



public interface BookingRepository extends JpaRepository<Booking, Long> {

	
  	Optional<Booking> findByCarAndCustomer(Car car, Customer customer);
  	
  	Optional<Booking> findByCarAndStatus(Car car, BookingStatus status);
}
