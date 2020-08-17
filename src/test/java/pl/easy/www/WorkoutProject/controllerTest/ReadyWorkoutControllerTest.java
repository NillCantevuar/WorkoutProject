package pl.easy.www.WorkoutProject.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReadyWorkoutControllerTest extends CurrentWorkoutAbility {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ReadyWorkoutService readyWorkoutService;
	@Autowired
	private CurrentWorkoutService currentWorkoutService;
	
	@Before
	public void clearAll () {
		 CurrentWorkout.workout = new ArrayList<>();
		 readyWorkoutService.deleteAllFromDB();
	}
	
	@Test
	public void should_load_readyWorkout_from_db_to_current_using_id() throws Exception {
		//given
		prepereNotEmptyList();
		List<WorkoutPice> expectedList = new ArrayList<>(CurrentWorkout.workout);
		ReadyWorkout savedReadyWorkout = readyWorkoutService.saveCurrentWorkoutToDB();
		currentWorkoutService.clearCurrentWorkout();
		//when
		mockMvc.perform(get("/api/readyWorkouts/loadSaved/"+savedReadyWorkout.getId()))
			.andExpect(status().isOk());
		//then 
		assertEquals(expectedList , currentWorkoutService.getList());
	}
	
	@Test
	public void should_save_current_workout_to_db() throws Exception {
		//given
		prepereNotEmptyList();
		ReadyWorkout expected = readyWorkoutService.getCurrentWorkoutAsReady();
		//when
		MvcResult result = mockMvc.perform(post("/api/readyWorkouts/saveCurrent/"))
					.andExpect(status().isOk())
					.andReturn();
		//then
		List<ReadyWorkout> allWorkouts = readyWorkoutService.getAllWorkoutsObjects();
		assertEquals(expected.getContent(), allWorkouts.get(0).getContent());
	}
	
	@Test
	public void should_get_readyWorkout_from_DB_using_id() throws Exception{
		//given
		addSingleReadyWorkoutToDB();
		List<ReadyWorkout> readyWorkouts = readyWorkoutService.getAllWorkoutsObjects();
		String expectedString = objectMapper.writeValueAsString(readyWorkouts.get(0));
		//when
		MvcResult result = mockMvc.perform(get("/api/readyWorkouts/getWorkout/"+readyWorkouts.get(0).getId()))
					.andExpect(status().isOk()).andReturn();
		//then
		String contetnJson = result.getResponse().getContentAsString();
		assertEquals(expectedString, contetnJson);
	}
	
	@Test
	public void should_save_ReadyWorkout_to_DB_using_object() throws Exception {
		//given
		ReadyWorkout generatedReadyWorkout = generateSingleReadyWorkout();
		String generatedAsString = objectMapper.writeValueAsString(generatedReadyWorkout);
		//when
		mockMvc.perform(post("/api/readyWorkouts/saveWorkout")
				.contentType("application/json").content(generatedAsString))
			.andExpect(status().isOk());
		//then
		List<ReadyWorkout> allWorkouts = readyWorkoutService.getAllWorkoutsObjects();
		assertEquals(generatedReadyWorkout.getContent(), allWorkouts.get(0).getContent());
		
	}
	
	@Test
	public void should_give_currentWorkout_as_ReadyWorkout() throws Exception {
		//given
		prepereNotEmptyList();
		String currentString = currentWorkoutService.currentWorkoutToString();
		//when
		MvcResult result = mockMvc.perform(get("/api/readyWorkouts/getCurrent"))
			.andExpect(status().isOk())
			.andReturn();
		//then
		String contetnJson = result.getResponse().getContentAsString();
		ReadyWorkout parsedObject = objectMapper.readValue(contetnJson, ReadyWorkout.class);
		assertEquals(currentString, parsedObject.getContent());
		
	}
	@Ignore
	@Test
	public void check_save_ReadyWorkout_from_DB_at_desktop () throws Exception{
		//given
		ReadyWorkout addedReadyWorkout = addSingleReadyWorkoutToDB();
		//when
		mockMvc.perform(get("/api/readyWorkouts/saveReadyWorkoutAtDesktop/"+addedReadyWorkout.getId()));
		//then
		
	}
	
	@Test
	public void should_update_readyWorkout_in_DB() throws Exception{
		//given
		List<ReadyWorkout> readyWrokouts = generateThreeDiffrentReadyWorkouts();
		ReadyWorkout savedReadyWorkout = readyWorkoutService.saveReadyWorkoutToDB(readyWrokouts.get(0));
		String updateContent = readyWrokouts.get(1).getContent();
		//when
		mockMvc.perform(patch("/api/readyWorkouts/updateReadyWorkout/"+savedReadyWorkout.getId())
				.contentType("application/json")
				.content(updateContent)).andExpect(status().isOk());
		//then
		List<ReadyWorkout> allReadyWorkouts = readyWorkoutService.getAllWorkoutsObjects();
		assertEquals(updateContent, allReadyWorkouts.get(0).getContent());
	}
	
	@Test
	public void should_delete_all_from_DB () throws Exception {
		//given
		addThreeReadyWorkoutsToDB();
		assertEquals(3, readyWorkoutService.getAllWorkoutsObjects().size());
		//when
		mockMvc.perform(delete("/api/readyWorkouts/deleteAll/"))
			.andExpect(status().isOk());
		//then
		assertEquals(0, readyWorkoutService.getAllWorkoutsObjects().size());
		
	}

}
