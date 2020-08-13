package pl.easy.www.WorkoutProject.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	ExerciseService exerciseService;
	
	public void addExercise(int id, int series, int repetitions ) {
		
		Exercise exerciseFromDB = exerciseService.giveById(id);
		
		CurrentWorkout.workout.add(new ExerciseElement(
				exerciseFromDB.getExerciseGroupName(),
				exerciseFromDB.getExerciseDirectName(),
				exerciseFromDB.getMuscleGroupName(), 
				exerciseFromDB.getMuscleDirectName(),
				series,
				repetitions));
	}
	
	public void addExercise(CompleteRequest request) { 
		
		ExerciseRequest exerciseR = request.getExerciseRequest();
		VolumeRequest volume = request.getVolumeRequest();
		
		Exercise addedExercise = exerciseService.add(new Exercise(exerciseR));
		ExerciseElement exerciseElement = new ExerciseElement(addedExercise,volume.getSeries(),volume.getRepetitions());
		CurrentWorkout.workout.add(exerciseElement);
			
	}
	
	public void addExercise(int id, VolumeRequest volumeRequest) {
		
		Exercise exerciseFromDB = exerciseService.giveById(id);
		
		CurrentWorkout.workout.add(new ExerciseElement(
				exerciseFromDB.getExerciseGroupName(),
				exerciseFromDB.getExerciseDirectName(),
				exerciseFromDB.getMuscleGroupName(), 
				exerciseFromDB.getMuscleDirectName(),
				volumeRequest.getSeries(),
				volumeRequest.getRepetitions()));
	}
	
	
	public void addBreak(BreakRequest request) {
		
		CurrentWorkout.workout.add(BreakMapper.map(request));
		
	}
	public void addBreak(int duration) {
		CurrentWorkout.workout.add(new BreakElement(duration));
	}
	
	public void delete(int index) {
		CurrentWorkout.workout.remove(index);
	}

	
	public void replaceByExerciseAtIndex(int idDB,int currId,int series,int repetitions) {
		
		Exercise exercise =exerciseService.giveById(idDB);
		
		CurrentWorkout.workout.set(currId, new ExerciseElement(
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName(),
				series,
				repetitions));
		
		
		
	}
	public void replaceByExerciseAtIndex(int idDB,int currId,VolumeRequest volumeRequest) {
		
		Exercise exercise =exerciseService.giveById(idDB);
		
		CurrentWorkout.workout.set(currId, new ExerciseElement(
				exercise.getExerciseGroupName(),
				exercise.getExerciseDirectName(),
				exercise.getMuscleGroupName(),
				exercise.getMuscleDirectName(),
				volumeRequest.getSeries(),
				volumeRequest.getRepetitions()));
	}
	public void replaceByExerciseRequest(CompleteRequest completeRequest,int currId) {
		Exercise addedExercise = exerciseService.add(new Exercise(completeRequest.getExerciseRequest()));	
		CurrentWorkout.workout.set(currId, new ExerciseElement(
				addedExercise.getExerciseGroupName(),
				addedExercise.getExerciseDirectName(),
				addedExercise.getMuscleGroupName(),
				addedExercise.getMuscleDirectName(),
				completeRequest.getVolumeRequest().getSeries(),
				completeRequest.getVolumeRequest().getRepetitions()));
	}
	
	public void replaceByBreakAtIndex(int duration, int index) {
		CurrentWorkout.workout.set(index, new BreakElement(duration));
	}
	
	
	public List<WorkoutPice> getList(){
		return CurrentWorkout.workout;
	} 
	
	public String saveWorkout () {
		StringBuilder sb = new StringBuilder(); 
		for (WorkoutPice workoutPice : CurrentWorkout.workout) {	
			sb.append(workoutPice.toString());	
		}
		return sb.toString();
	}
	
	public void saveAtDesktop(String workout) {
		String userHomeFolder =System.getProperty("user.home");
		
		File textFile = new File(userHomeFolder,generateLocalDateFileName()+".txt");
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(workout);
			out.flush();
			out.close();
		}catch (Exception e) {
			
			System.out.println(e.getMessage()); 
		}
	} 
	
	public void clearCurrentWorkout() {
		CurrentWorkout.workout.clear(); 
	}
	
	private String generateLocalDateFileName () {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss")); 
	}
	

	
	public void overvriteCurrentWorkout(List<WorkoutPice> newCurrentList) {
		CurrentWorkout.workout = newCurrentList;
		
	}

	public void replaceByBreakAtIndex(BreakRequest breakRequest, int index) {
		CurrentWorkout.workout.set(index, new BreakElement(breakRequest));
		
	}
	
	
	

	
	
	
	

}
