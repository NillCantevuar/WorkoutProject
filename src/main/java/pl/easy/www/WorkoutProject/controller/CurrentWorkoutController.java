package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.flow.router.OptionalParameter;

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
	
	@PostMapping("/addExercise/complete")
	public void addExercise (@RequestBody CompleteRequest request) {
		
		service.addExercise(request);
	}
	@PostMapping("/addExercise/param/{id}")
	public void addExercise(@PathVariable int id,@RequestParam int series,@RequestParam int repetitions) {
		
		
		
	}
	@PostMapping("/addExercise/volume/{id}")
	public void addExercise(@PathVariable int id,@RequestBody VolumeRequest volumeRequest) {
		
		
	}
	
	@PostMapping(value ="/addBreak/complete")
	public void addBreak (@RequestBody BreakRequest request) {
		service.addBreak(request);
	}
	
	@PostMapping("/addBreak/numeric")
	public void addBreak (@RequestParam int id) {
		
	}
	
	@PostMapping (value ="/replaceByExercise")
	public void replaceByExerciseAtIndex(@RequestBody CompleteRequest request, int index){
		service.replaceByExerciseAtIndex(request, index);	
	}
	
	@PostMapping ("/replaceByExercise/{current}")
	public void replaceByExerciseAtIndex(@PathVariable int current,@RequestParam int dbIndex) {
		
	}
	
	@PostMapping (value ="/replaceByBreak")
	public void replaceByBreakAtIndex(@RequestBody BreakRequest request,@RequestParam int index) {
		service.replaceByBreakAtIndex(request,index);
	}
	
	@PostMapping ("/replaceByBreak/{index}")
	public void replaceByBreakAtIndex(@PathVariable int index,@RequestParam int duration) {
		
	}
	
	
	@PostMapping (value="/saveWorkout")	
	public void saveWorkout() {
		
		service.saveWorkout();
	}
	

	 

}
