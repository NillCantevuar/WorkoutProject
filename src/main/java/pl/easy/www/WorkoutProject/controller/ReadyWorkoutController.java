package pl.easy.www.WorkoutProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;

@RestController
@Transactional
@RequestMapping("/api/readyWorkouts")

public class ReadyWorkoutController {
	
	@Autowired
	ReadyWorkoutService service;
	
	@GetMapping("/loadSaved/{id}")
	public void loadSaved (@PathVariable int id) {
		service.loadReadyWorkoutToCurrent(id);
		
	}

	
}
