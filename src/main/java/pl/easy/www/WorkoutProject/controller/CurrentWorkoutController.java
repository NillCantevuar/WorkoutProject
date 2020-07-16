package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;

@RestController
@Transactional
@RequestMapping("/api/currentWorkout")
public class CurrentWorkoutController {
	
	@Autowired
	CurrentWorkoutService service;
	
	@GetMapping
	public @ResponseBody List<WorkoutPice> getCurrentWorkout(){
		
		return service.getList();
	}
	
	@PostMapping("/addExercise")
	public void addExercise (@RequestBody CompleteRequest request) {
		
		service.addExercise(request);
	}
	
	@PostMapping(value ="/addBreak")
	public void addBreak (@RequestBody BreakRequest request) {
		
		service.addBreak(request);
	}
	
	@PostMapping (value ="/replaceByExercise")
	public void replaceByExerciseAtIndex(@RequestBody CompleteRequest request, int index){
		
		service.replaceByExerciseAtIndex(request, index);	
	}
	
	@PostMapping (value ="/replaceByBreak")
	public void replaceByBreakAtIndex(@RequestBody BreakRequest request, int index) {
		
		service.replaceByBreakAtIndex(request,index);
	}
	

	

}
