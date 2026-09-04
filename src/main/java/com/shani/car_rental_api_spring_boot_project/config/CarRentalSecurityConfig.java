package com.shani.car_rental_api_spring_boot_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class CarRentalSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(c -> c.disable())
	        .cors(Customizer.withDefaults())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/v1/auth/**").permitAll()
	            .requestMatchers("/api/v1/carOwner/**").permitAll()
	            .requestMatchers("/api/v1/customer/**").permitAll()
	            .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
	            .requestMatchers("/carController/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(Customizer.withDefaults());

	    return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
