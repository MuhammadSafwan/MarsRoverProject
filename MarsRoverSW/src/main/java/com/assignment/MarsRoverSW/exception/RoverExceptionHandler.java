/**
 * 
 */
package com.assignment.MarsRoverSW.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.MarsRoverSW.exception.InvalidInputException;
import com.assignment.MarsRoverSW.exception.RoverExceptionResponse;

/**
 * @author safwan
 *
 */
public class RoverExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<RoverExceptionResponse> handleException(InvalidInputException e) {
		RoverExceptionResponse exception = new RoverExceptionResponse(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}

}
