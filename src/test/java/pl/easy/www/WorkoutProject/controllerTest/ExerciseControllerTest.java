package pl.easy.www.WorkoutProject.controllerTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.ExerciseAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest extends ExerciseAbility{

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ExerciseService exerciseService;
	
	@Before
	public void clearAll () {
		exerciseService.clearDB();
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_give_list_of_all_exercises () throws Exception {
		//given
		List<Exercise> exerciesList = generateThreeExercises();
		String expectedJson = objectMapper.writeValueAsString(exerciesList);
		exerciseService.add(exerciesList.get(0));
		exerciseService.add(exerciesList.get(1));
		exerciseService.add(exerciesList.get(2));
		//when
		MvcResult result =  mockMvc.perform(get("/api/exercises"))
		.andExpect(status().isOk()).andReturn();
		String contentJson =result.getResponse().getContentAsString();
		//then
		 List<Exercise> resultList =objectMapper.readValue(
				 contentJson, new TypeReference<List<Exercise>>() { });
			    
		
		for (int i = 0; i < resultList.size(); i++) {
			
			assertEquals(exerciesList.get(i).showInfo(), resultList.get(i).showInfo());
		}
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_two_exercise_using_request() throws Exception {
		//given
		List<Exercise> expectedList =generateThreeExercises();
		String firstJson = objectMapper.writeValueAsString(expectedList.get(0));
		String secondJson = objectMapper.writeValueAsString(expectedList.get(1));
		//when
	    mockMvc.perform(post("/api/exercises/add").contentType("application/json").content(firstJson)).andExpect(status().isOk());
		mockMvc.perform(post("/api/exercises/add").contentType("application/json").content(secondJson)).andExpect(status().isOk());
		//then
		List<Exercise> actualList = exerciseService.getAllExercises();
		assertEquals(expectedList.get(0).showInfo(),actualList.get(0).showInfo());
		
		
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_delete_two_exercises_using_id() throws Exception {
		List<Exercise> exerciesList =generateThreeExercises();
		Exercise addedExercise1 = exerciseService.add(exerciesList.get(0));
		Exercise addedExercise2 =  exerciseService.add(exerciesList.get(1));
		exerciseService.add(exerciesList.get(2));
		//when
		mockMvc.perform(delete("/api/exercises/delete/"+addedExercise1.getId()))
			.andExpect(status().isOk());
		mockMvc.perform(delete("/api/exercises/delete/"+addedExercise2.getId()))
			.andExpect(status().isOk());
		//then
		List<Exercise> actualList = exerciseService.getAllExercises();
		assertEquals(actualList.size(), 1);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_return_exercise_using_id() throws Exception {
		List<Exercise> exercisesList = generateThreeExercises();
		Exercise addedExercise =exerciseService.add(exercisesList.get(0));
		//when
		MvcResult result =  mockMvc.perform(get("/api/exercises/getById/"+addedExercise.getId()))
			.andExpect(status().isOk()).andReturn();
		//then
		String contentJson =result.getResponse().getContentAsString();
		Exercise exerciseRecived =objectMapper.readValue(
				 contentJson, Exercise.class);
		
		assertEquals(addedExercise.showInfo(),exerciseRecived.showInfo() );
		
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_update_exercise_using_request_at_id() throws Exception {
		List<Exercise> exerciseList = generateThreeExercises();
		Exercise addedExercise = exerciseService.add(exerciseList.get(0));
		String freshExerciseJson = objectMapper.writeValueAsString(exerciseList.get(1));
		//when
		MvcResult result = mockMvc.perform(patch("/api/exercises/update/"+addedExercise.getId())
				.contentType("application/json")
				.content(freshExerciseJson))
			.andExpect(status().isOk()).andReturn();
		//then
		String contentJson =result.getResponse().getContentAsString();
		Exercise exerciseRecived =objectMapper.readValue(
				 contentJson, Exercise.class);
		assertEquals(exerciseList.get(1).showInfo(), exerciseRecived.showInfo());
	}
		
		
		
		
		
	}
	

