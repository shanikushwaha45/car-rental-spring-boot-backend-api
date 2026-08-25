package com.shani.car_rental_api_spring_boot_project.entity;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	private int id;
	

	@ManyToMany(mappedBy = "bookings")
	private List<Customer> customers;
}