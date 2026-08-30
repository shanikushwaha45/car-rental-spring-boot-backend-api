package com.shani.car_rental_api_spring_boot_project.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;   // <-- correct import

@Data
@Entity
@Table(name = "car-owner")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_owner_seq")
    @SequenceGenerator(name = "car_owner_seq", sequenceName = "car_owner_seq", allocationSize = 1, initialValue = 2001)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String licenseNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "car_owner_roles",
        joinColumns = @JoinColumn(name = "car_owner_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "carOwner", fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude          // <-- correct annotation
    @EqualsAndHashCode.Exclude
    private List<Car> cars;
}