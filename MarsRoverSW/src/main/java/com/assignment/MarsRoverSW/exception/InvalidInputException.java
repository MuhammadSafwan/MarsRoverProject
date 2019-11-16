/**
 * 
 */
package com.assignment.MarsRoverSW.exception;

/**
 * @author safwan
 *
 */
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(Throwable cause) {
		super(cause);
	}
	
	

}
