package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PostMapping("/addExercise/param/{idDB}")
	public void addExercise(@PathVariable int idDB,@RequestParam int series,@RequestParam int repetitions) {
			service.addExercise(idDB, series, repetitions);
	}
	
	@PostMapping("/addExercise/volume/{idDB}")
	public void addExercise(@PathVariable int idDB,@RequestBody VolumeRequest volumeRequest) {
		service.addExercise(idDB, volumeRequest);
	}
	
	@PostMapping("/addExercise/complete")
	public void addExercise(@RequestBody CompleteRequest completeRequest) {
		service.addExercise(completeRequest);
	}
	
	@PostMapping("/addBreak/complete")
	public void addBreak (@RequestBody BreakRequest request) {
		service.addBreak(request);
	}
	
	@PostMapping("/addBreak/numeric")
	public void addBreak (@RequestParam int duration) {
		service.addBreak(duration);
	}
	// tu
	//z malej
	@PostMapping ("/replaceByExerciseParams/{current}")
	public void replaceByExerciseAtIndex(@PathVariable int current,@RequestParam int dbIndex,@RequestParam int series, @RequestParam int repetitions){
		service.replaceByExerciseAtIndex(dbIndex,current,series,repetitions);	
	}
	// tu
	//z malej
	@PostMapping ("/replaceByExerciseVolume/{current}")
	public void replaceByExerciseAtVolume(@PathVariable int current,@RequestParam int dbIndex,@RequestBody VolumeRequest volumeRequest) {
		service.replaceByExerciseAtIndex(dbIndex, current, volumeRequest);
	}
	// tu
	//z malej
	@PatchMapping("/replaceByExerciseComplete/{current}")
	public void replaceByExerciseComplete(@PathVariable int current,@RequestBody CompleteRequest completeRequest) {
		service.replaceByExerciseRequest(completeRequest, current);
	}
	//z malej
	@PostMapping ("/replaceByBreak/Request/{current}")
	public void replaceByBreakAtIndex(@RequestBody BreakRequest request,@PathVariable int current) {
		service.replaceByBreakAtIndex(request.getDuration(),current);
	}
	//z malej
	@PostMapping ("/replaceByBreak/Duration/{current}")
	public void replaceByBreakAtIndex(@PathVariable int current,@RequestParam int duration) {
		service.replaceByBreakAtIndex(duration,current);
	}
	
	@DeleteMapping("/delete/{current}")
	public void delete(@PathVariable int current) {
		service.delete(current);
	}
	
	@PostMapping ("/saveWorkout")	
	public void saveWorkout() {
		service.saveAtDesktop(service.saveWorkout());
	}
	
	@DeleteMapping("/clear")
	public void clear() {
		service.clearCurrentWorkout();
	}
	

	 

}
