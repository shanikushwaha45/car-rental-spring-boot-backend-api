
package com.shani.car_rental_api_spring_boot_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerRequestDto;
import com.shani.car_rental_api_spring_boot_project.dto.CarOwnerResponseDto;
import com.shani.car_rental_api_spring_boot_project.entity.CarOwner;
import com.shani.car_rental_api_spring_boot_project.exception.EmailAlreadyExistsException;
import com.shani.car_rental_api_spring_boot_project.mapper.CarOwnerMapper;
import com.shani.car_rental_api_spring_boot_project.repository.CarOwnerRepository;

@ExtendWith(MockitoExtension.class)
class CarOwnerServiceTest {

    @Mock
    private CarOwnerRepository carOwnerRepository;

    @Mock
    private CarOwnerMapper carOwnerMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CarOwnerService carOwnerService;

    private CarOwner carOwner;
    private CarOwner savedCarOwner;
    private CarOwnerRequestDto requestDTO;
    private CarOwnerResponseDto responseDTO;

    @BeforeEach
    public void createSetupForRegisterTest() {

        // Request DTO
        requestDTO = new CarOwnerRequestDto();
        requestDTO.setEmail("MASOOD@GMAIL.COM".toLowerCase().trim());
        requestDTO.setPassword("password123");

        // Entity created by mapper
        carOwner = new CarOwner();
        carOwner.setEmail("masood@gmail.com");
        carOwner.setPassword("password123");

        // Entity returned by database
        savedCarOwner = new CarOwner();
        savedCarOwner.setId(1L);
        savedCarOwner.setEmail("masood@gmail.com");
        savedCarOwner.setPassword("encodedPassword");

        // Response DTO
        responseDTO = new CarOwnerResponseDto();
        responseDTO.setId(1L);
        responseDTO.setEmail("masood@gmail.com");
    }

    @Test
    public void testRegisterCarOwner_Success() {

        // Arrange
        when(carOwnerRepository.existsByEmail("masood@gmail.com"))
                .thenReturn(false);

        when(carOwnerMapper.toCarOwner(requestDTO))
                .thenReturn(carOwner);

        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");

        when(carOwnerRepository.save(carOwner))
                .thenReturn(savedCarOwner);

        when(carOwnerMapper.toCarOwnerResponseDto(savedCarOwner))
                .thenReturn(responseDTO);

        // Act
        CarOwnerResponseDto result =
                carOwnerService.registerCarOwner(requestDTO);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("masood@gmail.com", result.getEmail());

        // Verify repository email check
        verify(carOwnerRepository, times(1))
                .existsByEmail("masood@gmail.com");

        // Verify mapper
        verify(carOwnerMapper, times(1))
                .toCarOwner(requestDTO);

        // Verify password encoding
        verify(passwordEncoder, times(1))
                .encode("password123");

        // Verify save
        verify(carOwnerRepository, times(1))
                .save(carOwner);

        // Verify response mapping
        verify(carOwnerMapper, times(1))
                .toCarOwnerResponseDto(savedCarOwner);
    }

    @Test
    public void testRegisterCarOwner_Failed_EmailAlreadyExists() {

        // Arrange
        when(carOwnerRepository.existsByEmail("masood@gmail.com"))
                .thenReturn(true);

        // Act + Assert
        EmailAlreadyExistsException exception =
                assertThrows(
                        EmailAlreadyExistsException.class,
                        () -> carOwnerService.registerCarOwner(requestDTO)
                );

        // Verify exception message
        assertEquals(
                "Car owner with email masood@gmail.com already exists",
                exception.getMessage()
        );

        // Verify email check happened
        verify(carOwnerRepository, times(1))
                .existsByEmail("masood@gmail.com");

        // These should NOT be called when email already exists
        verify(carOwnerMapper, times(0))
                .toCarOwner(any(CarOwnerRequestDto.class));

        verify(passwordEncoder, times(0))
                .encode(any(String.class));

        verify(carOwnerRepository, times(0))
                .save(any(CarOwner.class));

        verify(carOwnerMapper, times(0))
                .toCarOwnerResponseDto(any(CarOwner.class));
    }
}
