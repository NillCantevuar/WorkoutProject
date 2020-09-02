package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;
import pl.easy.www.WorkoutProject.protocol.request.CompleteRequest;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.protocol.request.VolumeRequest;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;

//Kontroler zarządzający obsługą wewnętrznej Listy zajmującej się przechwowywaniem obecnie edytowanego treningu.


@RestController
@Transactional
@RequestMapping("/api/currentWorkout")
public class CurrentWorkoutController {
	
	@Autowired
	private CurrentWorkoutService service; 
	
	//Pobieranie wszystkich elementow z obecnie edytowanego trenigu

	@GetMapping
	public @ResponseBody List<WorkoutPice>getCurrentWorkout(){
		return service.getList();
	}

	//Trzy endpointy sluzace do dodawania cwiczen do treningu, z roznymi typami danych wejsciowych

	@PostMapping("/addExercise/param/{idDB}")
	public void addExercise(@PathVariable int idDB,@RequestParam int series,@RequestParam int repetitions) {
			service.addExercise(idDB, series, repetitions);
	}
	@PostMapping("/addExercise/volume/{idDB}")
	public void addExercise(@PathVariable int idDB,@RequestBody VolumeRequest volumeRequest) {
		service.addExercise(idDB, volumeRequest);
	}
	@PostMapping("/addExercise/complete")
	public ResponseEntity<CollectionModel<WorkoutPice>> addExercise(@RequestBody CompleteRequest completeRequest) {
		Link link  = WebMvcLinkBuilder.linkTo(CurrentWorkoutController.class).slash("").withSelfRel();	
		ExerciseElement element = service.addExercise(completeRequest);
		CollectionModel<WorkoutPice> model = new CollectionModel<WorkoutPice>(CurrentWorkout.workout, link);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	//Dwa endpointy sluzace do dodawanie przerw do treningu, z roznymi typami danych wejsicowych

	@PostMapping("/addBreak/complete")
	public void addBreak (@RequestBody BreakRequest request) {
		service.addBreak(request);
	}
	@PostMapping("/addBreak/numeric")
	public void addBreak (@RequestParam int duration) {
		service.addBreak(duration);
	}

	//Trzy endpointy sluzace do podmieniania elementu treningu na cwiczenie z roznymi typami danych wejsciowych.
	//Index miejsca podmiany jest przekazywany poprzez PathVariable
	
	@PostMapping ("/replaceByExercise/params/{current}")
	public void replaceByExerciseAtIndex(@PathVariable int current,@RequestParam int dbIndex,@RequestParam int series, @RequestParam int repetitions){
		service.replaceByExerciseAtIndex(dbIndex,current,series,repetitions);	
	}
	@PostMapping ("/replaceByExercise/volume/{current}")
	public void replaceByExerciseAtVolume(@PathVariable int current,@RequestParam int dbIndex,@RequestBody VolumeRequest volumeRequest) {
		service.replaceByExerciseAtIndex(dbIndex, current, volumeRequest);
	}
	@PatchMapping("/replaceByExercise/complete/{current}")
	public void replaceByExerciseComplete(@PathVariable int current,@RequestBody CompleteRequest completeRequest) {
		service.replaceByExerciseRequest(completeRequest, current);
	}

	//Trzy endpointy sluzace do podmieniania elementu treningu na przerwe z roznymi typami danych wejsciowych.
	//Index miejsca podmiany jest przekazywany poprzez PathVariable

	@PostMapping ("/replaceByBreak/request/{current}")
	public void replaceByBreakAtIndex(@RequestBody BreakRequest request,@PathVariable int current) {
		service.replaceByBreakAtIndex(request,current);
	}
	@PostMapping ("/replaceByBreak/duration/{current}")
	public void replaceByBreakAtIndex(@PathVariable int current,@RequestParam int duration) {
		service.replaceByBreakAtIndex(duration,current);
	}

	//Endpoint usuwający element na indexie podanym w PathVariable
	
	@DeleteMapping("/delete/{current}")
	public void delete(@PathVariable int current) {
		service.delete(current);
	}

	//Endpoint zapisujacy obecnie edytowany trening w postaci pliku tekstowego na pulpicie
	
	@GetMapping ("/saveWorkout")	
	public void saveWorkout() {
		service.saveAtDesktop(service.currentWorkoutToString()); 
	}

	//Endpoint zwracajacy obecnie edytowany trening w postaci Stringa

	@GetMapping ("/toString")
	public @ResponseBody String currentWorkoutToString() {
		return service.currentWorkoutToString();
	}

	//Endpoint czyszczący obecnie edytowany trening
	
	@DeleteMapping("/clear")
	public void clear() {
		service.clearCurrentWorkout();
	}
	

	 

}
