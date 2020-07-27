package pl.easy.www.WorkoutProject.servicesTest;


import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.mappers.ExerciseMapper;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.ExerciseAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrentWorkoutServiceTestWithContext extends ExerciseAbility{
		
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
	public void should_add_one_exercise_equal_size_from_DB () {
		//given
		int serices = 3;
		int repetitions =10;
		Exercise exercise = addOneExercise();
		//when
		currentService.addExercise(exercise.getId(),serices,repetitions);
		//then
		Assert.assertEquals(CurrentWorkout.workout.size(),1);
	
	}
	@Test
	public void should_add_one_exercise_equal_object_from_DB_using_VR () {
		//given
		VolumeRequest volumeRequest = new VolumeRequest(3, 10);
		Exercise exercise = addOneExercise();
		WorkoutPice wp = new ExerciseElement(exercise,volumeRequest.getSeries(),volumeRequest.getRepetitions());
		//when
		currentService.addExercise(exercise.getId(), volumeRequest);
		//
		List<WorkoutPice> allExercises = currentService.getList();
		Assert.assertEquals(allExercises.get(0).getInfo(),wp.getInfo());
		
	}
	
	@Test
	public void should_add_one_exercise_equal_object_from_DB () {
		//given
		int serices = 3;
		int repetitions =10;
		Exercise exercise = addOneExercise();
		WorkoutPice wp = new ExerciseElement(exercise,serices,repetitions);
		//when
		currentService.addExercise(exercise.getId(),serices,repetitions);
		//then
		List<WorkoutPice> allExercises = currentService.getList();
		Assert.assertEquals(allExercises.get(0).getInfo(),wp.getInfo());
	
	}
	
	@Test
	public void should_add_multiple_exercises_equal_size_from_DB() {
		//given
		int firstSeries=1;
		int secondSeries=2;
		int thirdSeries=3;
		int firstRepetitions=10;
		int secondRepetitions=20;
		int thirdRepetitions=30;
		List<Exercise> threeExercises = addThreeExercisesToDB();
		//when
		currentService.addExercise(threeExercises.get(0).getId(), firstSeries, firstRepetitions);
		currentService.addExercise(threeExercises.get(1).getId(), secondSeries, secondRepetitions);
		currentService.addExercise(threeExercises.get(2).getId(), thirdSeries, thirdRepetitions);
		//then
		List<WorkoutPice> allExercises = currentService.getList();
		
		Assert.assertEquals(CurrentWorkout.workout.size(),3);	
		
	}
	@Test
	public void should_add_multiple_exercises_equal_object_from_DB() {
		//given
		int firstSeries=1;
		int secondSeries=2;
		int thirdSeries=3;
		int firstRepetitions=10;
		int secondRepetitions=20;
		int thirdRepetitions=30;
		List<Exercise> threeExercises = addThreeExercisesToDB();
		//when
		currentService.addExercise(threeExercises.get(0).getId(), firstSeries, firstRepetitions);
		currentService.addExercise(threeExercises.get(1).getId(), secondSeries, secondRepetitions);
		currentService.addExercise(threeExercises.get(2).getId(), thirdSeries, thirdRepetitions);
		//then
		List<WorkoutPice> allExercises = currentService.getList();
		
		Assert.assertEquals(allExercises.get(0).getInfo()
				,new ExerciseElement(threeExercises.get(0),firstSeries, firstRepetitions).getInfo());
		Assert.assertEquals(allExercises.get(1).getInfo()
				,new ExerciseElement(threeExercises.get(1),secondSeries, secondRepetitions).getInfo());
		Assert.assertEquals(allExercises.get(2).getInfo()
				,new ExerciseElement(threeExercises.get(2),thirdSeries, thirdRepetitions).getInfo());	
	}
	 
	@Test
	public void should_replce_exercise_by_exercise_by_id_equal_exercise() {
		//given
		List<Exercise> threeExercises = addThreeExercisesToDB();
		int index = 1;
		ExerciseElement ee = new ExerciseElement(threeExercises.get(1),2,40);
		currentService.addExercise(threeExercises.get(0).getId(), 3, 10);
		//when
		currentService.replaceByExerciseAtIndex(threeExercises.get(1).getId(), 0, ee.getSeries(), ee.getRepetitions());
		//then
		Assert.assertEquals(CurrentWorkout.workout.get(0).getInfo(),ee.getInfo()); 
		
	}
	

}
