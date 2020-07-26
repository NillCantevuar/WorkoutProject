package pl.easy.www.WorkoutProject.servicesTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.support.ExerciseAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrentWorkoutServiceTestWithContext extends ExerciseAbility{
	
	
	
//	@Test
//	public void should_add_one_exercise_equal_size () {
//		//given
//		
//		CompleteRequest completeRequest = generateSingleCompleteRequest();
//		//when
//		currentWorkoutService.addExercise(completeRequest);
//		//then
//		Assert.assertEquals(CurrentWorkout.workout.size(),1);
//	
//		
//	}
//	

}
