package pl.easy.www.WorkoutProject.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

}
