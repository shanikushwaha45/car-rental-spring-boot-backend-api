package com.shani.car_rental_api_spring_boot_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shani.car_rental_api_spring_boot_project.dto.BookingRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.BookingResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.Booking;
import com.shani.car_rental_api_spring_boot_project.entity.Car;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.entity.Customer;
import com.shani.car_rental_api_spring_boot_project.enums.BookingStatus;
import com.shani.car_rental_api_spring_boot_project.mapper.BookingMapper;
import com.shani.car_rental_api_spring_boot_project.repository.BookingRepository;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;
import com.shani.car_rental_api_spring_boot_project.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;

	private final CarService carService;

	private final CustomerRepository customerRepository;

	private final CarOwnerRepository carOwnerRepository;
	
	private final BookingMapper bookingMapper;

	public ResponseEntity<?> bookCarService(Long carId, HttpSession httpSession,BookingRequestDto bookingRequestDTO) {

		String email = (String) httpSession.getAttribute("customerSession");

		if (email == null) {

			return ResponseEntity.ok()
					.body("you are not logged in please login and then try");

		}

		// fetch car by carId and check if it is available or not
		Car car = carService.getCarByIdService(carId);

		// fetch customer email from session and check if he has already booked the car
		// or not
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("customer not found"));

		bookingRepository.findByCarAndCustomer(car, customer).ifPresent(b -> {
			throw new RuntimeException("you have already booked this car");
		});

		Booking booking = new Booking();
		booking.setJourneyDate(bookingRequestDTO.getJourneyDate());
		booking.setSource(bookingRequestDTO.getSource());
		booking.setDestination(bookingRequestDTO.getDestination());
		booking.setCar(car);
		booking.setCustomer(customer);
		booking.setStatus(BookingStatus.PENDING);

		Booking booking2 = bookingRepository.save(booking);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new BookingResponseDto("car booked successfully", booking2));
	}

	public ResponseEntity<BookingResponseDto> getBookingByIdService(Long bookingId) {

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("booking not found"));

		return ResponseEntity.ok().body(new BookingResponseDto("booking fetched successfully", booking));
	}

	/**
	 *  
	 * @param httpSession
	 * @return
	 */
	public ResponseEntity<?> getPendingBookingForCarOwner(HttpSession httpSession) {

		String email = (String) httpSession.getAttribute("carOwnerSession");

		if (email == null) {

			return ResponseEntity.ok()
					.body("you are not logged in please login and then try");

		}
		
		List<BookingResponseDto> pendingBookings = new ArrayList<>();
		
		CarOwner carOwner=carOwnerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("car owner not found"));

		carOwner.getCars().forEach(car -> {
			bookingRepository.findByCarAndStatus(car, BookingStatus.PENDING).ifPresent(booking -> {
				
				pendingBookings.add(bookingMapper.toBookingResponseDTO(booking));
			});
		});
		
		return ResponseEntity.ok().body(pendingBookings);
	}
	
	public ResponseEntity<?> confirmedOrRejectBookingStatus(Long bookingId, BookingStatus status,HttpSession httpSession) {
		
		String email =(String) httpSession.getAttribute("carOwnerSession");
		
		if(email == null) {
			
			return ResponseEntity.ok().body("you are not logged in please login and then try");
		}
		
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("booking not found"));
		
		booking.setStatus(status);
		
		Booking updatedBooking = bookingRepository.save(booking);
		
		return ResponseEntity.ok().body(new BookingResponseDto("booking status updated successfully", updatedBooking));
	}
}