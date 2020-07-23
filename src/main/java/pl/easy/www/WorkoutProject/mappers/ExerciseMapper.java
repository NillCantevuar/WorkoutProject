package pl.easy.www.WorkoutProject.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.protocol.response.ExerciseResponse;

public class ExerciseMapper {
	
	
	public static ExerciseResponse mapExercise (Exercise exercise) {
		
		return new ExerciseResponse(
				exercise.getId(),
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName());
		
	}
	
	public static List<ExerciseResponse> mapExerciseList(List<Exercise> inputList){
		
		List<ExerciseResponse> exerciseResponses = inputList.stream().map(
				e -> ExerciseMapper.mapExercise(e))
				.collect(Collectors.toList());
		
		return exerciseResponses;
	}
	
	public static ExerciseElement mapToElement(Exercise exercise) {
		
		return new ExerciseElement(exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName());
		
	}
	
	public static ExerciseElement eRequestToEElement (CompleteRequest completeRequest) {
		
		return new ExerciseElement(
				completeRequest.getExerciseRequest().getExerciseGroupName(),
				completeRequest.getExerciseRequest().getExerciseDirectName(),
				completeRequest.getExerciseRequest().getMuscleGroupName(),
				completeRequest.getExerciseRequest().getMuscleDirectName(),
				completeRequest.getVolumeRequest().getSeries(),
				completeRequest.getVolumeRequest().getRepetitions());
		
		
	}
	
	

}
