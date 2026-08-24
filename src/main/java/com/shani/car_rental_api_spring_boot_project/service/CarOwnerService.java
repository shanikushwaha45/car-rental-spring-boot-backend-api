package com.shani.car_rental_api_spring_boot_project.service;

import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarOwnerService {
	private final CarOwnerRepository carOwnerRepository;
	public CarOwner getCarOwnerByEmail(String email) {
		return carOwnerRepository.findByEmail(email).get();
	}
}
