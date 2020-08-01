package pl.easy.www.WorkoutProject.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
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
		assertEquals(expectedJson, contentJson);
	}
	
}
