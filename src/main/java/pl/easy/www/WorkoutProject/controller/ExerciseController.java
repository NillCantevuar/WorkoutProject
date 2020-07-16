package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api")
public class ExerciseController {

	@Autowired
	private ExerciseService service;

	@PostMapping(value = "/add")
	public ExerciseResponse add(@RequestBody ExerciseRequest request) {

		return ExerciseMapper
				.mapExercise(service.add(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(),
						request.getMuscleGroupName(),
						request.getMuscleDirectName()
						));		
	}

	@GetMapping
	public @ResponseBody List<ExerciseResponse> getAllExercises() {
		
		return ExerciseMapper.mapExerciseList(
				service.getAllExercises());
	}
	
	
	@GetMapping(value = "/getById/{id}")
	public @ResponseBody ExerciseResponse getExerciseById(@PathVariable int id) {
		
		return ExerciseMapper
				.mapExercise(service.giveById(id));
	}
	
	@PatchMapping(value = "/update/{id}")
	public ExerciseResponse	update(@RequestBody ExerciseRequest request, @PathVariable int id){
		
		return ExerciseMapper
				.mapExercise(service.update(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(),
						request.getMuscleGroupName(),
						request.getMuscleDirectName(),
						id
						));		
		
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestParam int id) {	
		service.deleteByIndex(id);
	}
	
	
	
	
	// wyciagniecie pojedynczego cwiczenie
	
	

}
