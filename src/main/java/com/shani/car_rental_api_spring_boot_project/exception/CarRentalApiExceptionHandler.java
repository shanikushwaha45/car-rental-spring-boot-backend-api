package com.shani.car_rental_api_spring_boot_project.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CarRentalApiExceptionHandler {
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(CarRentalApiExceptionHandler.class);
	
	@ExceptionHandler(value=EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> emailAlreadyExistsException(EmailAlreadyExistsException e){
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
		
		LOGGER.warn("Email Already exists { }"+e.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(errorResponse);
	}
	
	@ExceptionHandler(value = RoleNotFoundException.class)
	public ResponseEntity<ErrorResponse> roleNotFoundException(RoleNotFoundException e){
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		LOGGER.warn("given role is not found"+e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
	}
	
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e){
		List<FieldError> fieldErrors=e.getFieldErrors();
		Map<String,String> map=new LinkedHashMap<>();
		for(FieldError error:fieldErrors) {
			map.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	@ExceptionHandler(value = InvalidEmailException.class)
	public ResponseEntity<ErrorResponse> invalidEmailException(InvalidEmailException e){
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		LOGGER.warn("email is wrong "+e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	@ExceptionHandler(value = InvalidPasswordException.class)
	public ResponseEntity<ErrorResponse> invalidPasswordException(InvalidPasswordException e){
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		LOGGER.warn("password is wrong "+e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(value=CarNotFoundException.class)
	public ResponseEntity<ErrorResponse> carNotFoundException(CarNotFoundException e){
	

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		LOGGER.warn("given Car is not found"+e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
	}
	

}
