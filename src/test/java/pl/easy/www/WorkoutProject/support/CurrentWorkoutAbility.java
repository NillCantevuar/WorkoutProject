package pl.easy.www.WorkoutProject.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;

public class CurrentWorkoutAbility extends ExerciseAbility{
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private ReadyWorkoutService readyWorkoutService;
	
	public void prepereNotEmptyList() {
		
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest bRequest= generateSingleBreakRequest(); 
		CompleteRequest eRequest = generateSingleCompleteRequest();
		ExerciseElement exerciseElement = new ExerciseElement(eRequest);
		
		service.addBreak(bRequest.getDuration());
		CurrentWorkout.workout.add(exerciseElement);
		service.addBreak(bRequest.getDuration());
		CurrentWorkout.workout.add(exerciseElement);
		service.addBreak(bRequest.getDuration());
		CurrentWorkout.workout.add(exerciseElement);	
	}
	public void prepereNotEmptyListV2(){
		CompleteRequest eRequest = generateSingleCompleteRequest();
		ExerciseElement exerciseElement = new ExerciseElement(eRequest);
		
		CurrentWorkout.workout.add(exerciseElement);
		CurrentWorkout.workout.add(exerciseElement);
		CurrentWorkout.workout.add(exerciseElement);
		CurrentWorkout.workout.add(exerciseElement);
		CurrentWorkout.workout.add(exerciseElement);
		CurrentWorkout.workout.add(exerciseElement);
	}
	public void prepereNotEmptyListV3(){
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest bRequest= generateSingleBreakRequest();
	
		service.addBreak(bRequest.getDuration());
		service.addBreak(bRequest.getDuration());
		service.addBreak(bRequest.getDuration());
		service.addBreak(bRequest.getDuration());
		service.addBreak(bRequest.getDuration());
		service.addBreak(bRequest.getDuration());
	}

	public ExerciseRequest generateSingleExerciseRequest() {
		ExerciseRequest exerciseRequest = new ExerciseRequest();
		
		exerciseRequest.setExerciseGroupName("Dipy");
		exerciseRequest.setExerciseDirectName("Na drazku");
		exerciseRequest.setMuscleGroupName("rece");
		exerciseRequest.setMuscleDirectName("triceps");
				
		return exerciseRequest;	
	}
	public ExerciseRequest generateSingleDifferentExerciseRequest() {
		ExerciseRequest exerciseRequest = new ExerciseRequest();
		
		exerciseRequest.setExerciseGroupName("Stanie na rekach");
		exerciseRequest.setExerciseDirectName("pompki");
		exerciseRequest.setMuscleGroupName("rece");
		exerciseRequest.setMuscleDirectName("barki");
				
		return exerciseRequest;	
	}
	
	public VolumeRequest generateSingleVolumeRequest() {
		return  new VolumeRequest(3, 10);
		
	}
	public VolumeRequest generateSingleDifferentVolumeRequest() {
		return  new VolumeRequest(5, 11);
		
	}
	
	public CompleteRequest generateSingleCompleteRequest() {
		return new CompleteRequest(
				generateSingleExerciseRequest(),
				generateSingleVolumeRequest());
	}
	public CompleteRequest generateSingleDifferentCompleteRequest() {
		return new CompleteRequest(
				generateSingleDifferentExerciseRequest(),
				generateSingleDifferentVolumeRequest());
	}
	
	public BreakRequest generateSingleBreakRequest() {
		return new BreakRequest(30);
	}
	public BreakRequest generateSingleDifferentBreakRequest() {
		return new BreakRequest(60);
	}
	
	public ReadyWorkout generateSingleReadyWorkout () {
		prepereNotEmptyList();
		return readyWorkoutService.getCurrentWorkoutAsReady();	
	}
	public List<ReadyWorkout> generateThreeDiffrentReadyWorkouts(){
		List<ReadyWorkout> workouts = new ArrayList<>();
		
		prepereNotEmptyList();
		workouts.add(readyWorkoutService.getCurrentWorkoutAsReady());
		CurrentWorkout.workout.clear();
		
		prepereNotEmptyListV2();
		workouts.add(readyWorkoutService.getCurrentWorkoutAsReady());
		CurrentWorkout.workout.clear();
		
		prepereNotEmptyListV3();
		workouts.add(readyWorkoutService.getCurrentWorkoutAsReady());
		CurrentWorkout.workout.clear();
		
		return workouts;
	}
	
	public ReadyWorkout addSingleReadyWorkoutToDB(){
		return readyWorkoutService.saveReadyWorkoutToDB(generateSingleReadyWorkout()); 
	}
	
	public List<ReadyWorkout> addThreeReadyWorkoutsToDB(){
		List<ReadyWorkout> workouts = generateThreeDiffrentReadyWorkouts();
		
		for (ReadyWorkout readyWorkout : workouts) {
			readyWorkoutService.saveReadyWorkoutToDB(readyWorkout);
		}
		return readyWorkoutService.getAllWorkoutsObjects();
	}
	 


}
