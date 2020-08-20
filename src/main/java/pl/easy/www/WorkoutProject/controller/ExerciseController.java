package pl.easy.www.WorkoutProject.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.mappers.ExerciseMapper;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.response.ExerciseResponse;
import pl.easy.www.WorkoutProject.repository.ExerciseRepository;
import pl.easy.www.WorkoutProject.services.ExerciseService;

@RestController
@Transactional
@RequestMapping("/api/exercises")
public class ExerciseController {

	@Autowired
	private ExerciseService service;

	@PostMapping("/add")
	public ResponseEntity<ExerciseResponse> add(@RequestBody ExerciseRequest request) {
		
		//??
		HttpHeaders headers = new HttpHeaders();
		headers.add("Test", "Test");
		
		ExerciseResponse response = ExerciseMapper
				.mapExercise(service.add(new Exercise(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(), 
						request.getMuscleGroupName(),
						request.getMuscleDirectName()
						)));		
		
		return ResponseEntity.ok().headers(headers).body(response);
		
		
	}
	
	@GetMapping
	public @ResponseBody List<Exercise> getAllExercises() {
		return service.getAllExercises();
	}
	 
	@GetMapping("/getById/{id}")
	public @ResponseBody ExerciseResponse getExerciseById(@PathVariable int id) {
		return ExerciseMapper
				.mapExercise(service.giveById(id));
	}
	
	@PatchMapping("/update/{id}")
	public ExerciseResponse	update(@RequestBody ExerciseRequest request, @PathVariable int id){
		return ExerciseMapper
				.mapExercise(service.update(new Exercise(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(),
						request.getMuscleGroupName(),
						request.getMuscleDirectName()),
						id
						));		 
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {	
		service.delete(id);
	}
	
	@DeleteMapping("/clear")
	public void clear() {
		service.clearDB();
	}
	
}
