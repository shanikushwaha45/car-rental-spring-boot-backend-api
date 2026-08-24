package com.shani.car_rental_api_spring_boot_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_sequence")
	@SequenceGenerator(name="role_sequence",sequenceName = "role_sequence",allocationSize = 1,initialValue = 3331)
	private Long id;
	@Column(nullable=false,unique = true,name="name")
	private String name;
}
