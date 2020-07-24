package pl.easy.www.WorkoutProject.servicesTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.mappers.BreakMapper;
import pl.easy.www.WorkoutProject.mappers.ExerciseMapper;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrentWorkoutServiceTest extends CurrentWorkoutAbility{
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	CurrentWorkoutService currentWorkoutService;
	
	
	@Before
	public void clear() {
		CurrentWorkout.workout = new ArrayList<>();
	}
	
	
	@Test
	public void should_add_one_exercise_equal_size () {
		//given
		
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		//when
		currentWorkoutService.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
		
	}
	
	@Test
	public void should_add_one_exercise_equal_size_from_DB () {
		//given
		
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		//when
		currentWorkoutService.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
		
	}
	
	@Test
	public void should_add_one_exercise_equal_exercise () {


		//given
		
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		ExerciseElement element = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		currentWorkoutService.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);
		
	}
	
	@Test
	public void should_add_multiple_exercises_equal_size () {
		//given
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		//when
		currentWorkoutService.addExercise(completeRequest);
		currentWorkoutService.addExercise(completeRequest);
		currentWorkoutService.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),3);
		
	}
	
	@Test
	public void should_add_one_break_equal_size () {
		//given
		
		BreakRequest request= generateSingleBreakRequest();
		//when
		currentWorkoutService.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
		
	}
	
	@Test
	public void should_add_one_break_equal_break () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		BreakElement element = new BreakElement(30);
		//when
		currentWorkoutService.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);
		
	}
	
	@Test
	public void should_add_multiple_breaks_equal_size () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		//when
		currentWorkoutService.addBreak(request);
		currentWorkoutService.addBreak(request);
		currentWorkoutService.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),3);
		
	}
	
	@Test
	public void should_add_one_break_and_exercise_equal_size () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		CompleteRequest eRequest = generateSingleCompleteRequest();
		//when
		currentWorkoutService.addBreak(request);
		currentWorkoutService.addExercise(eRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),2);
		
	}
	
	@Test
	public void sholud_delete_one_exercise_at_index() {
		//given
		prepereNotEmptyList();
		int index = 1;	
		//when
		currentWorkoutService.delete(index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(), 5);
	}
	
	@Test
	public void should_replce_exercise_by_exercise_equal_exercise() {
		//given
		prepereNotEmptyList();
		int index = 1;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		currentWorkoutService.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
		
	}
	
	@Test
	public void should_replce_exercise_by_exercise_notEqual_old_exercise() {
		//given
		prepereNotEmptyList();
		int index = 1;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(generateSingleCompleteRequest());
		//when
		currentWorkoutService.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertNotEquals(CurrentWorkout.workout.get(index),expected); 
		
	}
	
	@Test
	public void should_replce_break_by_exercise_equal__exercise() {
		//given
		prepereNotEmptyList();
		int index = 0;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		currentWorkoutService.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	
	}
	
	@Test
	public void should_replce_break_by_break_equal_break() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(breakRequest);
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_break_by_break_not_equal_old_break() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(generateSingleBreakRequest());
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertNotEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_exercise_by_break_equal_break() {
		//given
		prepereNotEmptyList();
		int index = 1;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(breakRequest);
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	@Ignore
	public void should_save_workout_to_string () {
		//given
		String expected ="Break: 30s\r\n" + 
				"\r\n" + 
				"//Dipy Na drazku\r\n" + 
				"Target: rece triceps\r\n" + 
				"Series: 3 Reps: 10\r\n" + 
				"\r\n" + 
				"Break: 30s\r\n" + 
				"\r\n" + 
				"//Dipy Na drazku\r\n" + 
				"Target: rece triceps\r\n" + 
				"Series: 3 Reps: 10\r\n" + 
				"\r\n" + 
				"Break: 30s\r\n" + 
				"\r\n" + 
				"//Dipy Na drazku\r\n" + 
				"Target: rece triceps\r\n" + 
				"Series: 3 Reps: 10\r\n" + 
				"\r\n" + 
				"";
		prepereNotEmptyList();
		//when
		String actual = currentWorkoutService.saveWorkout();
		//then
		Assert.assertEquals(expected, actual);
		
	} 
	
	@Test
	@Ignore
	public void check_saving_to_desktop() {
		prepereNotEmptyList();
		String text = currentWorkoutService.saveWorkout();
		currentWorkoutService.saveAtDesktop(text);
	}
	

}
