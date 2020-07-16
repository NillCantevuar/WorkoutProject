package pl.easy.www.WorkoutProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.mappers.ExerciseMapper;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.repository.ExerciseRepository;

@Service
public class ExerciseService {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	public Exercise add(String exerciseGroupName, String exerciseDirectName, String muscleGroupName, String muscleDirectName ) {		
		
		Exercise exercise = new Exercise(exerciseGroupName, exerciseDirectName, muscleGroupName, muscleDirectName);
		return exerciseRepository.save(exercise);
		
	}
	
	public List<Exercise> getAllExercises(){	
				return exerciseRepository.findAll();
	}
	
	public void delete (Exercise exercise) {
		exerciseRepository.delete(exercise);
	}
	
	public void deleteByIndex(int id) {
		exerciseRepository.deleteById(id);
	}
	
	public Exercise giveById(int id) {
		return exerciseRepository.findById(id).get();
	}
	
	public Exercise update(String exerciseGroupName, String exerciseDirectName, String muscleGroupName, String muscleDirectName,int id) {
		
		Exercise exercise = exerciseRepository.findById(id).get();
		
		exercise.setExerciseDirectName(exerciseDirectName);
		exercise.setExerciseGroupName(exerciseGroupName);
		exercise.setMuscleDirectName(muscleDirectName);
		exercise.setMuscleGroupName(muscleGroupName);
		
		return exerciseRepository.save(exercise);
		
		
	}
				
	
	
	

}
