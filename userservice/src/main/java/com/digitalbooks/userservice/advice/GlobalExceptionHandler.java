package com.digitalbooks.userservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbooks.userservice.exception.ErrorResponse;
import com.digitalbooks.userservice.exception.UserServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<?> handleUserServiceException(UserServiceException ex){
		
		ResponseEntity responseEntity = new ResponseEntity(
				new ErrorResponse(ex.getMessage()),HttpStatus.BAD_REQUEST);
		return responseEntity;
		
	}

}
