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
	
	public Exercise add(Exercise exercise) {		
		
		return exerciseRepository.save(exercise);
		
	}
	
	public List<Exercise> getAllExercises(){	
				return exerciseRepository.findAll();
	}
	
	public void delete (Exercise exercise) {
		exerciseRepository.delete(exercise);
	}
	
	public void delete(int id) {
		exerciseRepository.deleteById(id);
	}
	
	public Exercise giveById(int id) {
		return exerciseRepository.findById(id).get();
	}
	
	public Exercise update(Exercise updateExercise,int id) {
		 
		Exercise exercise = exerciseRepository.findById(id).get();
		
		exercise.setExerciseDirectName(updateExercise.getExerciseDirectName());
		exercise.setExerciseGroupName(updateExercise.getExerciseGroupName());
		exercise.setMuscleDirectName(updateExercise.getMuscleDirectName());
		exercise.setMuscleGroupName(updateExercise.getMuscleGroupName());
		
		return exerciseRepository.save(exercise);
		
	}
	 
	public void clearDB() {
		exerciseRepository.deleteAll();
	}
				
	
	
	

}
