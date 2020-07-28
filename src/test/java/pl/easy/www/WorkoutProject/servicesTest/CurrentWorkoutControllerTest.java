package pl.easy.www.WorkoutProject.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrentWorkoutControllerTest extends CurrentWorkoutAbility{
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	public void should_give_full_list () throws Exception {
		//given
		prepereNotEmptyList();
		List<WorkoutPice> currentList = new ArrayList<WorkoutPice>(CurrentWorkout.workout);
	    String listJson =gson.toJson(currentList, new ArrayList<>().getClass());
		//when
	    	MvcResult result = mockMvc.perform(get("/api/currentWorkout"))
				.andExpect(status()
						.isOk())
						.andReturn();		
	    	
		String contetnJson = result.getResponse().getContentAsString();
		
		
		//then
		assertEquals(listJson,contetnJson);
		
		
	}

}
