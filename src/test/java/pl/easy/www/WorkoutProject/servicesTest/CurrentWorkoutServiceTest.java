package pl.easy.www.WorkoutProject.servicesTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.stereotype.Service;

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
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(JUnit4.class)
public class CurrentWorkoutServiceTest extends CurrentWorkoutAbility{
	
	
	@Before
	public void clear() {
		CurrentWorkout.workout = new ArrayList<>();
	}
	
	
	@Test
	public void should_add_one_exercise_equal_size () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		//when
		service.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
		
	}
	
	@Test
	public void should_add_one_exercise_equal_exercise () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		ExerciseElement element = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		service.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);
		
	}
	@Test
	public void should_add_multiple_exercises_equal_size () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		//when
		service.addExercise(completeRequest);
		service.addExercise(completeRequest);
		service.addExercise(completeRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),3);
		
	}
	
	@Test
	public void should_add_one_break_equal_size () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest request= generateSingleBreakRequest();
		//when
		service.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
		
	}
	
	@Test
	public void should_add_one_break_equal_break () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest request= generateSingleBreakRequest();
		BreakElement element = new BreakElement(30);
		//when
		service.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);
		
	}
	
	@Test
	public void should_add_multiple_breaks_equal_size () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest request= generateSingleBreakRequest();
		//when
		service.addBreak(request);
		service.addBreak(request);
		service.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),3);
		
	}
	
	@Test
	public void should_add_one_break_and_exercise_equal_size () {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest request= generateSingleBreakRequest();
		CompleteRequest eRequest = generateSingleCompleteRequest();
		//when
		service.addBreak(request);
		service.addExercise(eRequest);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),2);
		
	}
	
	@Test
	public void sholud_delete_one_exercise_at_index() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 1;	
		//when
		service.delete(index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(), 5);
	}
	
	
	@Test
	public void should_replce_exercise_by_exercise_equal_exercise() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 1;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		service.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
		
	}
	
	

	@Test
	public void should_replce_exercise_by_exercise_notEqual_old_exercise() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 1;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(generateSingleCompleteRequest());
		//when
		service.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertNotEquals(CurrentWorkout.workout.get(index),expected); 
		
	}
	
	
	@Test
	public void should_replce_break_by_exercise_equal__exercise() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 0;
		CompleteRequest completeRequest =generateSingleDifferentCompleteRequest();
		ExerciseElement expected = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		service.replaceByExerciseAtIndex(completeRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	
	}
	
	
	@Test
	public void should_replce_break_by_break_equal_break() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(breakRequest);
		//when
		service.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_break_by_break_not_equal_old_break() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(generateSingleBreakRequest());
		//when
		service.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertNotEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_exercise_by_break_equal_break() {
		//given
		CurrentWorkoutService service = new CurrentWorkoutService();
		prepereNotEmptyList();
		int index = 1;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.map(breakRequest);
		//when
		service.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	
	

}
