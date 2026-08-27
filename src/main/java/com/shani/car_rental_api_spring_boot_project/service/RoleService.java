package com.shani.car_rental_api_spring_boot_project.service;

import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.entity.Role;
import com.shani.car_rental_api_spring_boot_project.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;
	
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}
}
