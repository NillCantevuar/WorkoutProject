package pl.easy.www.WorkoutProject.currentWorkout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;

public class CurrentWorkout {
	
	
	public static List<WorkoutPice> workout ;

	public CurrentWorkout() {
		CurrentWorkout.workout = new ArrayList<>();
	}

	public CurrentWorkout(ArrayList<WorkoutPice> workout) {
		CurrentWorkout.workout = workout;
	}
	
	
	
	
	

}
