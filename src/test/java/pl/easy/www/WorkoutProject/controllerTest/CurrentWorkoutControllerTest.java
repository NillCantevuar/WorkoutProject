package pl.easy.www.WorkoutProject.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrentWorkoutControllerTest extends CurrentWorkoutAbility{
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired 
	CurrentWorkoutService currentService;
	@Autowired
	ExerciseService exerciseService;
	
	@Before
	public void clearAll () {
		exerciseService.clearDB();
		currentService.clearCurrentWorkout();
	}
	
	@Test
	public void should_give_full_list () throws Exception {
		//given
		prepereNotEmptyList();
		List<WorkoutPice> currentList = new ArrayList<WorkoutPice>(CurrentWorkout.workout);
	    String listJson =objectMapper.writeValueAsString(currentList);
		//when
	    MvcResult result = mockMvc.perform(get("/api/currentWorkout"))
			.andExpect(status()
					.isOk())
					.andReturn();			
		String contetnJson = result.getResponse().getContentAsString();
		//then
		assertEquals(listJson,contetnJson);
	}
	
//	@Test
//	public void should_add_exercise_using_complete_request_expect_equal_exercise() throws Exception {
//		//given
//		CompleteRequest completeRequest = generateSingleCompleteRequest();
//		ExerciseElement expectedElement = new ExerciseElement(completeRequest);
//		String jsonRequest = objectMapper.writeValueAsString(completeRequest);
//		//when
//		mockMvc.perform(post("/api/currentWorkout/addExercise/complete")
//				.contentType("application/json")
//				.content(jsonRequest))
//				.andExpect(status().isOk())
//			.andReturn();
//		//then
//		WorkoutPice element =  CurrentWorkout.workout.get(0);
//		assertEquals(expectedElement.showInfo() ,element.showInfo());
//	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_using_id_leading_to_db_and_params() throws Exception {
		//given
	    Exercise addedToDb =addOneExercise();
	    int series = 3;
	    int repetitions = 10;
	    ExerciseElement expectedElement = new ExerciseElement(addedToDb, series, repetitions);
		//when
		mockMvc.perform(post("/api/currentWorkout/addExercise/param/"+addedToDb.getId())
				.param("series",String.valueOf(series))
				.param("repetitions", String.valueOf(repetitions)))
			.andExpect(status().isOk());
		//then
		WorkoutPice resoultElement = CurrentWorkout.workout.get(0);
		assertEquals(expectedElement.showInfo(), resoultElement.showInfo());
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_using_id_leading_to_db_and_volume_request() throws Exception {
		//given
		 Exercise addedToDb =addOneExercise();
		 VolumeRequest volumeRequest = new VolumeRequest(3,10);
		 ExerciseElement expectedElement = new ExerciseElement(addedToDb, volumeRequest.getSeries(), volumeRequest.getRepetitions());
		 String jsonRequest = objectMapper.writeValueAsString(volumeRequest);
		//when
	 	mockMvc.perform(post("/api/currentWorkout/addExercise/volume/"+addedToDb.getId())
				.contentType("application/json")
				.content(jsonRequest))
			.andExpect(status().isOk()).andReturn();
		//then
	 	WorkoutPice resoultElement = CurrentWorkout.workout.get(0);
		assertEquals(expectedElement.showInfo(), resoultElement.showInfo());
	}
	
	@Test
	public void should_add_break_using_break_request() throws Exception {
		//given
		int time = 120;
		BreakRequest breakRequest = new BreakRequest(time);
		BreakElement expectedElement = new BreakElement(time);
		String jsonRequest = objectMapper.writeValueAsString(breakRequest);
		//when
		mockMvc.perform(post("/api/currentWorkout/addBreak/complete/")
				.contentType("application/json")
				.content(jsonRequest))
			.andExpect(status().isOk()).andReturn();
		//then
		WorkoutPice resoultElement = CurrentWorkout.workout.get(0);
		assertEquals(expectedElement.showInfo(), resoultElement.showInfo());
	}
	@Test
	public void should_add_break_using_int_param() throws Exception {
		//given
		int time = 120;
		BreakElement expectedElement = new BreakElement(time);
		//when
		mockMvc.perform(post("/api/currentWorkout/addBreak/numeric/")
				.param("duration", String.valueOf(time)))
			.andExpect(status().isOk()).andReturn();
		//then
		WorkoutPice resoultElement = CurrentWorkout.workout.get(0);
		assertEquals(expectedElement.showInfo(), resoultElement.showInfo());
	}
	
	@Test
	public void should_replace_by_break_at_index_by_request() throws Exception {
		//given
		int currIndex = 0;
		int time = 120;
		CurrentWorkout.workout.add(currIndex,new BreakElement(90));
		BreakRequest breakRequest = new BreakRequest(time);
		BreakElement breakElement = new BreakElement(time);
		String jsonRequest =objectMapper.writeValueAsString(breakRequest);
		//when
		mockMvc.perform(post("/api/currentWorkout/replaceByBreak/Request/"+String.valueOf(currIndex))
				.contentType("application/json")
				.content(jsonRequest))
			.andExpect(status().isOk());
		//then
		WorkoutPice resoultElement = CurrentWorkout.workout.get(currIndex);
		assertEquals(breakElement.showInfo(), resoultElement.showInfo());
	}
	@Test
	public void should_replace_by_break_at_index_by_duration() throws Exception {
		//given
		int currIndex = 0;
		int duration = 120;
		CurrentWorkout.workout.add(currIndex,new BreakElement(90));
		BreakElement breakElement = new BreakElement(duration);
		//when
		mockMvc.perform(post("/api/currentWorkout/replaceByBreak/Duration/"+String.valueOf(currIndex))
				.param("duration", String.valueOf(duration)))
			.andExpect(status().isOk());
		//then
		WorkoutPice resoultElement = CurrentWorkout.workout.get(currIndex);
		assertEquals(breakElement.showInfo(),resoultElement.showInfo() );
	}
	@Test
	public void should_delete_element_by_id () throws Exception {
		//given 
		int currIndex = 0;
		CurrentWorkout.workout.add(currIndex,new BreakElement(90));
		//when
		mockMvc.perform(delete("/api/currentWorkout/delete/"+String.valueOf(currIndex)))
		.andExpect(status().isOk());
		//then
		assertThrows(IndexOutOfBoundsException.class, () -> CurrentWorkout.workout.get(currIndex));
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_replace_by_exercise_using_params() throws Exception {
		//given
		int series=3;
		int repetitions=10;
		int currIndex=0;
		List<Exercise> listFormDB = addThreeExercisesToDB();
		
		ExerciseElement exerciseElement = new ExerciseElement(listFormDB.get(0), 2, 20);
		ExerciseElement expectedElement = new ExerciseElement(listFormDB.get(1),series,repetitions);
		CurrentWorkout.workout.add(currIndex,exerciseElement);
		//when
		mockMvc.perform(post("/api/currentWorkout/replaceByExerciseParams/"+String.valueOf(currIndex))
				.param("dbIndex", "2")
				.param("repetitions", String.valueOf(repetitions))
				.param("series", String.valueOf(series)))
			.andDo(print())
			.andExpect(status().isOk());
		//then
		WorkoutPice resultElement =  CurrentWorkout.workout.get(currIndex);
		assertEquals(expectedElement.showInfo(), resultElement.showInfo());
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_replace_by_exercise_using_volume() throws Exception {
		//given
		int currIndex=0;
		VolumeRequest volumeRequest = new VolumeRequest(3, 10);
		List<Exercise> listFormDB = addThreeExercisesToDB();
		String jsonRequest =objectMapper.writeValueAsString(volumeRequest);
		
		ExerciseElement exerciseElement = new ExerciseElement(listFormDB.get(0), 2, 20);
		ExerciseElement expectedElement = new ExerciseElement(listFormDB.get(1),volumeRequest.getSeries(),volumeRequest.getRepetitions());
		CurrentWorkout.workout.add(currIndex,exerciseElement);
		//when
		mockMvc.perform(post("/api/currentWorkout/replaceByExerciseVolume/"+String.valueOf(currIndex))
				.param("dbIndex", "2")
				.contentType("application/json")
				.content(jsonRequest))
			.andDo(print())
			.andExpect(status().isOk());
		//then
		WorkoutPice resultElement =  CurrentWorkout.workout.get(currIndex);
		assertEquals(expectedElement.showInfo(), resultElement.showInfo());
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_clear_current_workout_list() throws Exception {
		//given
		prepereNotEmptyList();
		//when
		mockMvc.perform(delete("/api/currentWorkout/clear")).andExpect(status().isOk());
		//then
		assertEquals(CurrentWorkout.workout.size(), 0);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_to_current_and_db_by_request() throws Exception {
		//given
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		ExerciseElement expectedElement = new ExerciseElement(completeRequest);
		Exercise expectedExercise = new Exercise(completeRequest.getExerciseRequest());
		String jsonRequest = objectMapper.writeValueAsString(completeRequest);
		//when
		mockMvc.perform(post("/api/currentWorkout/addExercise/complete")
				.contentType("application/json")
				.content(jsonRequest))
			.andExpect(status().isOk());
		//then
	   List<Exercise> exercisesFromDb =	exerciseService.getAllExercises();
	   List<WorkoutPice> currentWorkoutList = currentService.getList();
	   
	   assertEquals(expectedExercise.showInfo(), exercisesFromDb.get(0).showInfo());
	   assertEquals(expectedElement.showInfo(), currentWorkoutList.get(0).showInfo());
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_to_current_by_request_and_replace_at_index () throws Exception {
	//given
	CompleteRequest completeRequest = generateSingleCompleteRequest();
	String jsonRequest = objectMapper.writeValueAsString(completeRequest);
	//when
	mockMvc.perform(patch("/api/currentWorkout/replaceByExerciseComplete")
			.contentType("application/json")
			.content(jsonRequest)).andExpect(status()
					.isOk());
	//then
	
	//czy dodało sie do DB
	//czy podmieniło na indexie w current Workout
	}
	
	

}
