package pl.easy.www.WorkoutProject.servicesTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.mappers.BreakMapper;
import pl.easy.www.WorkoutProject.mappers.ExerciseMapper;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;

import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;


@SpringBootTest
public class CurrentWorkoutServiceTest extends CurrentWorkoutAbility{
	
	private CurrentWorkoutService currentWorkoutService = new CurrentWorkoutService();
	
	@Before
	public void clear() {
		CurrentWorkout.workout = new ArrayList<>();
	} 
		
	@Test
	public void should_add_one_break_by_object_equal_size () {
		//given 
		BreakRequest request= generateSingleBreakRequest();
		//when
		currentWorkoutService.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);		
	}
	
	@Test
	public void should_add_one_break_by_number_equal_size () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		//when
		currentWorkoutService.addBreak(30);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);		
	}
	
	@Test
	public void should_add_one_break_by_object_equal_break () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		BreakElement element = new BreakElement(30);
		//when
		currentWorkoutService.addBreak(request);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);		
	}
	
	@Test
	public void should_add_one_break_by_number_equal_break () {
		//given
		BreakElement element = new BreakElement(30);
		//when
		currentWorkoutService.addBreak(30);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0),element);		
	}
	
	@Test
	public void should_add_multiple_breaks_by_object_equal_size () {
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
	public void should_add_multiple_breaks_by_number_equal_size () {
		//given
		BreakRequest request= generateSingleBreakRequest();
		//when
		currentWorkoutService.addBreak(10);
		currentWorkoutService.addBreak(20);
		currentWorkoutService.addBreak(30);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),3); 	
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
	public void should_replce_break_by_break_equal_break() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.mapToBreakElement(breakRequest);
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest.getDuration(), index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_break_by_break_not_equal_old_break() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.mapToBreakElement(generateSingleBreakRequest());
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest.getDuration(), index);
		//then
		Assert.assertNotEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	
	
	//to robie
	@Test
	public void should_replce_break_by_break_equal_break_using_request() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.mapToBreakElement(breakRequest);
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest.getDuration(), index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Test
	public void should_replce_break_by_break_not_equal_old_break_using_request() {
		//given
		prepereNotEmptyList();
		int index = 0;
		BreakRequest breakRequest = generateSingleDifferentBreakRequest();
		BreakElement expected = BreakMapper.mapToBreakElement(generateSingleBreakRequest());
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
		BreakElement expected = BreakMapper.mapToBreakElement(breakRequest);
		//when
		currentWorkoutService.replaceByBreakAtIndex(breakRequest, index);
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(index),expected); 
	}
	
	@Ignore
	@Test
	public void should_save_workout_to_string () {
		//given
		String expected ="Break -- 30s\r\n" + 
				"\r\n" + 
				"E:Dipy | Na drazku\r\n" + 
				"Target: rece | triceps\r\n" + 
				"Series: 3 Reps: 10\r\n" + 
				"\r\n" + 
				"Break -- 30s\r\n" + 
				"\r\n" + 
				"E:Dipy | Na drazku\r\n" + 
				"Target: rece | triceps\r\n" + 
				"Series: 3 Reps: 10\r\n" + 
				"\r\n" + 
				"Break -- 30s\r\n" + 
				"\r\n" + 
				"E:Dipy | Na drazku\r\n" + 
				"Target: rece | triceps\r\n" + 
				"Series: 3 Reps: 10";				
		prepereNotEmptyList();
		//when
		String actual = currentWorkoutService.currentWorkoutToString();
		//then
		Assert.assertEquals(expected, actual);	
	} 
	
	@Test
	public void check_saving_to_desktop() { 
		prepereNotEmptyList(); 
		currentWorkoutService.saveAtDesktop(currentWorkoutService.currentWorkoutToString());
	}
	
	@Test
	public void should_clear_current_workout() {
		//given
		prepereNotEmptyList();
		assertEquals(6, CurrentWorkout.workout.size()); 
		//when
		currentWorkoutService.clearCurrentWorkout();
		//then
		assertEquals(0, CurrentWorkout.workout.size());
	}
	
	
	

}
