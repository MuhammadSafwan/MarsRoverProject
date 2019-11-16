/**
 * 
 */
package com.assignment.MarsRoverSW.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.MarsRoverSW.model.Rover;
import com.assignment.MarsRoverSW.service.MarsRoverService;

/**
 * @author safwan
 *
 */

@RestController
@RequestMapping("/api")
public class MarsRoverController {
	
	@Autowired
	MarsRoverService marsRoverService;

	@GetMapping("/rover")
	public List<Rover> getRover(@RequestParam(value = "grid_dimension") String gridDimension,
			@RequestParam(value = "rover_starting_coordinates") List<String> roverStartingCoordinates,
			@RequestParam(value = "rover_instructions") List<String> roverInstructions) {
		return marsRoverService.getRover(gridDimension, roverStartingCoordinates, roverInstructions);
	}
}
