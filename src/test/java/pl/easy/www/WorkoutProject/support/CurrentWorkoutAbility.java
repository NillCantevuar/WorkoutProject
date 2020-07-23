package pl.easy.www.WorkoutProject.support;

import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;

public class CurrentWorkoutAbility {
	
	public void prepereNotEmptyList() {
		
		CurrentWorkoutService service = new CurrentWorkoutService();
		BreakRequest request= generateSingleBreakRequest();
		CompleteRequest eRequest = generateSingleCompleteRequest();
		
		service.addBreak(request);
		service.addExercise(eRequest);
		service.addBreak(request);
		service.addExercise(eRequest);
		service.addBreak(request);
		service.addExercise(eRequest);		
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
	


}
