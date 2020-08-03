package pl.easy.www.WorkoutProject.servicesTest;


import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
		Assert.assertEquals(allExercises.get(0).showInfo(),wp.showInfo());
		
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
		Assert.assertEquals(allExercises.get(0).showInfo(),wp.showInfo());
	
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
		
		Assert.assertEquals(allExercises.get(0).showInfo()
				,new ExerciseElement(threeExercises.get(0),firstSeries, firstRepetitions).showInfo());
		Assert.assertEquals(allExercises.get(1).showInfo()
				,new ExerciseElement(threeExercises.get(1),secondSeries, secondRepetitions).showInfo());
		Assert.assertEquals(allExercises.get(2).showInfo()
				,new ExerciseElement(threeExercises.get(2),thirdSeries, thirdRepetitions).showInfo());	
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
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
		Assert.assertEquals(CurrentWorkout.workout.get(0).showInfo(),ee.showInfo()); 
		
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_to_current_workout_and_to_db () {
		//given
		CompleteRequest completeRequest =  generateSingleCompleteRequest();
		ExerciseElement expectedElement = ExerciseMapper.eRequestToEElement(completeRequest);
		//when
		currentService.addExercise(completeRequest);
		//then
		List<Exercise> allExercisesFromDB =exerciseService.getAllExercises();
		
		assertEquals(expectedElement.showInfo(), CurrentWorkout.workout.get(0).showInfo());
		
		assertEquals(expectedElement.getExerciseDirectName(),allExercisesFromDB.get(0).getExerciseDirectName());
		assertEquals(expectedElement.getExerciseGroupName(),allExercisesFromDB.get(0).getExerciseGroupName());
		assertEquals(expectedElement.getMuscleDirectName(),allExercisesFromDB.get(0).getMuscleDirectName());
		assertEquals(expectedElement.getMuscleGroupName(),allExercisesFromDB.get(0).getMuscleGroupName());
		
	}
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Test
	public void should_add_exercise_using_request_to_db_and_replace_by_this_at_index () {
		//given
		int interestId = 0;
		Exercise addedExercise =  addOneExercise();
		currentService.addExercise(addedExercise.getId(),new VolumeRequest(5, 5));
		
		CompleteRequest completeRequest = generateSingleCompleteRequest();
		ExerciseElement expectedElement = ExerciseMapper.eRequestToEElement(completeRequest);
		Exercise expectedExercise = new Exercise(completeRequest.getExerciseRequest());
		//when
		currentService.replaceByExerciseRequest(completeRequest, interestId);
		//then
		List<Exercise> allExercisesFromDB =exerciseService.getAllExercises();
		assertEquals(allExercisesFromDB.get(1).showInfo(), expectedExercise.showInfo());
		assertEquals(expectedElement.showInfo(), CurrentWorkout.workout.get(0).showInfo());
		
	}
	
	
	

}
