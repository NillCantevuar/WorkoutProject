package pl.easy.www.WorkoutProject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.mappers.BreakMapper;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;

@Service
public class CurrentWorkoutService {
	

	public void addExercise(CompleteRequest request) {
		
		ExerciseRequest exercise = request.getExerciseRequest();
		VolumeRequest volume = request.getVolumeRequest();
		
		CurrentWorkout.workout.add(new ExerciseElement(
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName(),
				volume.getSeries(),
				volume.getRepetitions()));
			
	}
	
	public void addBreak(BreakRequest request) {
		
		CurrentWorkout.workout.add(BreakMapper.map(request));
		
	}
	

	public void delete(int index) {
		CurrentWorkout.workout.remove(index);
	}
	
	
	public void replaceByExerciseAtIndex(CompleteRequest request,int index) {
		
		ExerciseRequest exercise = request.getExerciseRequest();
		VolumeRequest volume = request.getVolumeRequest();
		
		CurrentWorkout.workout.set(index, new ExerciseElement(
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName(),
				volume.getSeries(),
				volume.getRepetitions()));
	}
	
	public void replaceByBreakAtIndex(BreakRequest request, int index) {
		
		CurrentWorkout.workout.set(index, new BreakElement(request.getDuration()));
	}
	
	
	//give list
	
	public List<WorkoutPice> getList(){
		return CurrentWorkout.workout;
	}

	
	
	
	

}
