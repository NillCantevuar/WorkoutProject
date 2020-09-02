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
	
	
	public static Exercise mapToExercise (ExerciseResponse exerciseResponse) {
		
		return new Exercise(
				exerciseResponse.getExerciseId(),
				exerciseResponse.getExerciseGroupName(),
				exerciseResponse.getExerciseDirectName(),
				exerciseResponse.getMuscleGroupName(),
				exerciseResponse.getMuscleDirectName());
		
	}
	
	public static ExerciseResponse mapToExerciseResponse (Exercise exercise) {
		
		return new ExerciseResponse(
				exercise.getId(),
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName());
		
	}
	public static List<ExerciseResponse> mapToListOfExerciseResponse(List<Exercise> inputList){
		List<ExerciseResponse> exerciseResponses = inputList.stream().map(
				e -> ExerciseMapper.mapToExerciseResponse(e))
				.collect(Collectors.toList());
		return exerciseResponses;
	}
	
	
	public static List<Exercise> mapToListOfExercise(List<ExerciseResponse> inputList){
		List<Exercise> exercises = inputList.stream().map(
				e -> ExerciseMapper.mapToExercise(e))
				.collect(Collectors.toList());
		return exercises;
	}
	
	
	public static ExerciseElement mapToExerciseElement(Exercise exercise) {
		
		return new ExerciseElement(exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName());
		
	}
	public static ExerciseElement mapToExerciseElement (CompleteRequest completeRequest) {
		
		return new ExerciseElement(
				completeRequest.getExerciseRequest().getExerciseGroupName(),
				completeRequest.getExerciseRequest().getExerciseDirectName(),
				completeRequest.getExerciseRequest().getMuscleGroupName(),
				completeRequest.getExerciseRequest().getMuscleDirectName(),
				completeRequest.getVolumeRequest().getSeries(),
				completeRequest.getVolumeRequest().getRepetitions());
		
		
	}
	
	

}
