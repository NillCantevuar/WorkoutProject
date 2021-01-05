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

//Kontroler sluzacy do wykonywania operacji na bazie danych przechowywujacej obiekty cwiczen.

@RestController
@Transactional
@RequestMapping("/api/exercises")
public class ExerciseController {

	@Autowired
	private ExerciseService service;

	//Endpoint sluzacy do dodawania cwiczenia do bazy danych

	@PostMapping("/add")
	public ResponseEntity<ExerciseResponse> add(@RequestBody ExerciseRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Test", "Test");
		ExerciseResponse response = ExerciseMapper
				.mapToExerciseResponse(service.add(new Exercise(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(), 
						request.getMuscleGroupName(),
						request.getMuscleDirectName()
						)));		
		return ResponseEntity.ok().headers(headers).body(response);
	}

	//Endpoint pobierajacy wszystkie cwiczenia z bazy danych
	
	@GetMapping
	public ResponseEntity<List<Exercise>>  getAllExercises() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("TestAll","TestAll");
		List<Exercise> exerciseList = service.getAllExercises();
		return ResponseEntity.ok().headers(httpHeaders).body(exerciseList);
	}

	//Endpoint pobierajacy pojedyncze cwiczenie na podstawie id
	 
	@GetMapping("/getById/{id}")
	public @ResponseBody ExerciseResponse getExerciseById(@PathVariable int id) {
		return ExerciseMapper
				.mapToExerciseResponse(service.giveById(id));
	}

	//Endpoint podmieniajacy cwiczenie w bazie danych na podtawie id bazy danych podanego w PathVariable
	
	@PatchMapping("/update/{id}")
	public ExerciseResponse	update(@RequestBody ExerciseRequest request, @PathVariable int id){
		return ExerciseMapper
				.mapToExerciseResponse(service.update(new Exercise(
						request.getExerciseGroupName(),
						request.getExerciseDirectName(),
						request.getMuscleGroupName(),
						request.getMuscleDirectName()),
						id
						));		 
	}

	//Endpoint usuwajacy cwiczenie z bazy danych na podstawie id podanego w PathVariable
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {	
		service.delete(id);
	}

	//Endpoint czyszczacy baze danych
	
	@DeleteMapping("/clear")
	public void clear() {
		service.clearDB();
	}
	
}
