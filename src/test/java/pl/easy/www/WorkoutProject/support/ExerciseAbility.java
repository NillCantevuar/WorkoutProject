package pl.easy.www.WorkoutProject.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.services.ExerciseService;

public class ExerciseAbility {
	
	@Autowired
	ExerciseService exerciseService ;
	
	public List<Exercise> generateThreeExercises() {
		
		List<Exercise> exercises = new ArrayList<>();
		Exercise exercise1 = new Exercise("Podciaganie","Nachwytem","Plecy","Szerokie");
		Exercise exercise2 = new Exercise("Pompki","Zwykle","Klatka","Cala");
		Exercise exercise3 = new Exercise("Dipy","Na Rownoleglych", "Rece","Triceps");
		
		exercises.add(exercise1);
		exercises.add(exercise2);
		exercises.add(exercise3);
		
		return exercises;
	}
	
	public List<Exercise> addThreeExercisesToDB() {
		
		List<Exercise> exercises = new ArrayList<>();
		Exercise exercise1 = new Exercise("Podciaganie","Nachwytem","Plecy","Szerokie");
		exerciseService.add(exercise1);
		exercises.add(exercise1);
		Exercise exercise2 = new Exercise("Pompki","Zwykle","Klatka","Cala");
		exerciseService.add(exercise2);
		exercises.add(exercise2);
		Exercise exercise3 = new Exercise("Dipy","Na Rownoleglych", "Rece","Triceps");
		exerciseService.add(exercise3);
		exercises.add(exercise3);
		
		return exercises;
	}
	
	public Exercise generateExercise() {
		
		return new Exercise("Podciaganie","Nachwytem","Plecy","Szerokie");
		
	}
	
	public Exercise addOneExercise() {
		
		return exerciseService.add(new Exercise("Podciaganie","Nachwytem","Plecy","Szerokie"));
		
	}

}
