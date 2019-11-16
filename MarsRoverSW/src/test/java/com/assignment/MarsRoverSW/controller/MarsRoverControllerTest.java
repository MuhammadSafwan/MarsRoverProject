/**
 * 
 */
package com.assignment.MarsRoverSW.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;


import com.assignment.MarsRoverSW.controller.MarsRoverController;
import com.assignment.MarsRoverSW.exception.InvalidInputException;
import com.assignment.MarsRoverSW.model.Rover;
import com.assignment.MarsRoverSW.service.MarsRoverService;

/**
 * @author safwan
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(MarsRoverController.class)
public class MarsRoverControllerTest {
	
	@Mock
	org.springframework.web.servlet.View mockView;

	private MockMvc mockMvc;
	
	@MockBean
	private MarsRoverService marsRoverService;

	@InjectMocks
	private MarsRoverController marsRoverController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(marsRoverController).setSingleView(mockView).build();
	}

	@Test
	public void shallReturnOkResponse() throws Exception {
		List<Rover> roverList = new ArrayList<>();
		roverList.add(new Rover(4, 4, "W"));
		when(marsRoverService.getRover(any(), anyList(), anyList())).thenReturn(roverList);
		mockMvc.perform(get("/api/rover?grid_dimension=5 5&rover_starting_coordinates=5 5 S&rover_instructions=MRM"))
				.andExpect(status().isOk());
	}
	
	@Test(expected = NestedServletException.class)
	public void shallReturnBadResponse() throws Exception {
		when(marsRoverService.getRover(any(), anyList(), anyList())).thenThrow(InvalidInputException.class);
		mockMvc.perform(get("/api/rover?grid_dimension=5 M&rover_starting_coordinates=5 5 S&rover_instructions=MRM"))
				.andExpect(status().isBadRequest());
	}
}
