/**
 * 
 */
package com.assignment.MarsRoverSW.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.MarsRoverSW.exception.InvalidInputException;
import com.assignment.MarsRoverSW.model.Rover;
import com.assignment.MarsRoverSW.service.MarsRoverService;
import com.assignment.MarsRoverSW.validator.MarsRoverValidator;

/**
 * @author safwan
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class MarsRoverServiceTest {
	
	@Mock
	private MarsRoverValidator marsRoverValidator;
	
	@InjectMocks
	private MarsRoverService marsRoverService;

	@Test
	public void shallReturnSingleRover() {
		String gridDimension = "5 5";
		List<String> roverStartingCoordinates = Arrays.asList("5 5 S");
		List<String> roverInstructions = Arrays.asList("MRM");
		
		when(marsRoverValidator.isValidGridDimension(any())).thenReturn(true);
		when(marsRoverValidator.isValidRoverPositions(anyList())).thenReturn(true);
		when(marsRoverValidator.isValidRoverInstructions(anyList())).thenReturn(true); 

		Rover resultRover = new Rover(4, 4, "W");
		List<Rover> result = marsRoverService.getRover(gridDimension, roverStartingCoordinates, roverInstructions);

		assertThat(result.get(0)).isEqualTo(resultRover);
	}

	@Test
	public void shallReturnMultipleRover() {
		String gridDimension = "5 5";
		List<String> roverStartingCoordinates = Arrays.asList("1 2 N", "3 3 E");
		List<String> roverInstructions = Arrays.asList("LMLMLMLMM", "MMRMMRMRRM");
		
		when(marsRoverValidator.isValidGridDimension(any())).thenReturn(true);
		when(marsRoverValidator.isValidRoverPositions(anyList())).thenReturn(true);
		when(marsRoverValidator.isValidRoverInstructions(anyList())).thenReturn(true);

		Rover rover1 = new Rover(1, 3, "N");
		Rover rover2 = new Rover(5, 1, "E");

		List<Rover> result = marsRoverService.getRover(gridDimension, roverStartingCoordinates, roverInstructions);

		assertThat(result.get(0)).isEqualTo(rover1);
		assertThat(result.get(1)).isEqualTo(rover2);
	}

	@Test(expected = InvalidInputException.class)
	public void shallThrowInvalidGridDimensionInputException() {
		when(marsRoverValidator.isValidGridDimension(any())).thenReturn(false);
		marsRoverService.getRover("5 N", Arrays.asList("1 2 N"), Arrays.asList("LMLM"));
	}
	
	@Test(expected = InvalidInputException.class)
	public void shallThrowInvalidRoverPositionInputException() {
		when(marsRoverValidator.isValidGridDimension(any())).thenReturn(true);
		when(marsRoverValidator.isValidRoverPositions(anyList())).thenReturn(false);
		marsRoverService.getRover("5 5", Arrays.asList("1 2 S"), Arrays.asList("LMLM"));
	}

}
