/**
 * 
 */
package com.assignment.MarsRoverSW.validator;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * @author safwan
 *
 */

@Component
public class MarsRoverValidator {
	
	private static final String GRID_DIMENSION = "\\d+\\s\\d+";
	private static final String ROVER_POSITION = "\\d+\\s\\d+\\s(N|W|E|S)";
	private static final String ROVER_INSTRUCTIONS = "(L|R|M)+";

	public boolean isValidGridDimension(String gridDimension) {
		return Pattern.matches(GRID_DIMENSION, gridDimension);
	}

	public boolean isValidRoverPositions(List<String> roverPositions) {
		for (String roverPosition : roverPositions) {
			if (!Pattern.matches(ROVER_POSITION, roverPosition)) {
				return false;
			}
		}
		return true;
	}

	public boolean isValidRoverInstructions(List<String> roverInstructions) {
		for (String roverInstruction : roverInstructions) {
			if (!Pattern.matches(ROVER_INSTRUCTIONS, roverInstruction)) {
				return false;
			}
		}
		return true;
	}

}
