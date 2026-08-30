package com.shani.car_rental_api_spring_boot_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.ToString;   // <-- correct import

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(
        name = "car_seq",
        sequenceName = "car_seq",
        allocationSize = 1,
        initialValue = 9001
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Column(nullable = false)
    private Double pricePerDay;

    @Column(nullable = false)
    private Double pricePerKm;

    @Column(nullable = false, length = 6)
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_owner_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude          // <-- correct annotation
    private CarOwner carOwner;
}