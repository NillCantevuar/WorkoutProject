package pl.easy.www.WorkoutProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;

@RestController
@Transactional
@RequestMapping("/api/readyWorkouts")

public class ReadyWorkoutController {
	
	@Autowired
	private ReadyWorkoutService service;
	
	@GetMapping("/loadSaved/{id}")
	public void loadSaved (@PathVariable int id) {
		service.loadReadyWorkoutToCurrentFromDB(id);
		
	}
	
	@PostMapping("/saveCurrent")
	public void saveCurrent () {
		service.saveCurrentWorkoutToDB();
	}
	
	@GetMapping("/getWorkout/{id}")
	public @ResponseBody ReadyWorkout getReadyWorkoutFromDB (@PathVariable int id){
	  return service.getReadyWorkoutFromDB(id);
	}
	
	@PostMapping("/saveWorkout")
	public @ResponseBody ReadyWorkout saveReadyWorkout (@RequestBody ReadyWorkout workoutToSave) {
		return service.saveReadyWorkoutToDB(workoutToSave);
	}
	
	@GetMapping("/getCurrent")
	public @ResponseBody ReadyWorkout getCurrent () {
		return service.getCurrentWorkoutAsReady();
	}
	
	@GetMapping("saveReadyWorkoutAtDesktop/{id}")
	public void saveReadyWorkoutAtDesktop(@PathVariable int id) {
		service.saveReadyWorkoutFromDBAtDesktop(id);
	}
	
	@PatchMapping("/updateReadyWorkout/{id}")
	public void updateReadyWorkout (@PathVariable int id, @RequestBody String freshContent) {
		service.updateWorkoutInDB(id, freshContent);
	}
}
