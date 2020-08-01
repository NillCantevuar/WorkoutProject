package pl.easy.www.WorkoutProject.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		File textFile = new File(userHomeFolder,"ZapisanyTrening.txt");
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
			out.write(workout);
			out.flush();
			out.close();
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		
		
	}
	public void clearCurrentWorkout() {
		CurrentWorkout.workout.clear();
	}
	
	

	
	
	
	

}
