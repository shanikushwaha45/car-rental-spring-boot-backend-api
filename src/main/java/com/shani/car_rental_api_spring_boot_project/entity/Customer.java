package com.shani.car_rental_api_spring_boot_project.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seq")
	@SequenceGenerator(name="customer_seq",sequenceName = "customer_seq",allocationSize = 1,initialValue = 1001)
	private Long id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(unique = true)
	private String phoneNumber;
	private String address;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="custome_roles",
			joinColumns = @JoinColumn(name="customer_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private List<Role> roles;
	@ManyToMany
	private List<Booking> bookings;
}
