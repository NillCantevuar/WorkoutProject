package pl.easy.www.WorkoutProject.currentWorkout;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Initializator {

	
	@PostConstruct
	private void initialization() {
		CurrentWorkout.workout = new ArrayList<>();
	}
	
	
}
