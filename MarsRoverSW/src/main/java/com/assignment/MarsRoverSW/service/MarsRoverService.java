/**
 * 
 */
package com.assignment.MarsRoverSW.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.MarsRoverSW.exception.InvalidInputException;
import com.assignment.MarsRoverSW.model.Rover;
import com.assignment.MarsRoverSW.validator.MarsRoverValidator;

/**
 * @author safwan
 *
 */

@Service
public class MarsRoverService {
	
	private int gridXCoordinate;
	private int gridYCoordinate;
	private static final List<String> orientations = Arrays.asList("E", "S", "W", "N");
	private int orientationIndex;

	@Autowired
	private MarsRoverValidator marsRoverValidator;

	public List<Rover> getRover(String gridDimension, List<String> roverStartingCoordinates,
			List<String> roverInstructions) throws InvalidInputException {

		if (!marsRoverValidator.isValidGridDimension(gridDimension)) {
			throw new InvalidInputException("Invalid grid dimensions. Please enter like 5 5.");
		}

		if (!marsRoverValidator.isValidRoverPositions(roverStartingCoordinates)) {
			throw new InvalidInputException(
					"Invalid rover coordinates or orientation. Please enter like 5 5 direction where direction should be N|S|E|W.");
		}

		if (!marsRoverValidator.isValidRoverInstructions(roverInstructions)) {
			throw new InvalidInputException("Invalid rover instructions. Please enter like RMRMLMLM from R|L|M.");
		}

		List<Rover> roverFinalPositions = new ArrayList<Rover>();

		String[] grid = gridDimension.split(" ");
		gridXCoordinate = Integer.parseInt(grid[0]);
		gridYCoordinate = Integer.parseInt(grid[1]);

		int roverCounter = 0;

		while (roverCounter < roverStartingCoordinates.size()) {
			int x = 0;
			int y = 0;

			String[] roverCoordinates = roverStartingCoordinates.get(roverCounter).split(" ");

			x = Integer.parseInt(roverCoordinates[0]);
			y = Integer.parseInt(roverCoordinates[1]);
			String startingOrientation = roverCoordinates[2];

			String inputMovement = roverInstructions.get(roverCounter);
			char[] movementArray = inputMovement.toCharArray();
			orientationIndex = orientations.indexOf(startingOrientation);

			Rover rover = getRoverFinalPosition(movementArray, x, y);

			rover.setX(boundaryCheckForX(rover.getX()));
			rover.setY(boundaryCheckForY(rover.getY()));

			roverFinalPositions.add(rover);
			roverCounter++;
		}

		return roverFinalPositions;

	}

	private Rover getRoverFinalPosition(char[] movementArray, int x, int y) {
		String currentOrientation = "";
		Rover rover = new Rover(x, y, currentOrientation);
		for (int i = 0; i < movementArray.length; i++) {

			if (movementArray[i] == 'L') {
				orientationIndex = orientationIndex - 1;
				if (orientationIndex < 0) {
					orientationIndex = orientations.size() - 1;
				}
				rover.setCurrentOrientation(orientations.get(orientationIndex));
			}

			else if (movementArray[i] == 'R') {
				orientationIndex = orientationIndex + 1;
				if (orientationIndex > orientations.size() - 1) {
					orientationIndex = 0;
				}
				rover.setCurrentOrientation(orientations.get(orientationIndex));
			}

			else if (movementArray[i] == 'M') {
				rover.setCurrentOrientation(orientations.get(orientationIndex));
				rover = changeOrientation(rover.getCurrentOrientation(), rover.getX(), rover.getY());
			}
		}
		return rover;
	}

	private Rover changeOrientation(String currentOrientation, int x, int y) {
		switch (currentOrientation) {
		case "N":
			y += 1;
			break;

		case "W":
			x -= 1;
			break;

		case "S":
			y -= 1;
			break;

		case "E":
			x += 1;
			break;
		}
		return new Rover(x, y, currentOrientation);

	}

	private int boundaryCheckForX(int x) {
		if (x > gridXCoordinate)
			x = gridXCoordinate;
		if (x < 0)
			x = 0;

		return x;
	}

	private int boundaryCheckForY(int y) {
		if (y > gridYCoordinate)
			y = gridYCoordinate;
		if (y < 0)
			y = 0;

		return y;
	}

}
