package pl.easy.www.WorkoutProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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

//Kontoler sluzacy do wykonywania operacji na bazie danych przechowujacej  gotowe treningi

@RestController
@Transactional
@RequestMapping("/api/readyWorkouts")

public class ReadyWorkoutController {
	
	@Autowired
	private ReadyWorkoutService service;

	//Endpoint pobierajacy gotowy trening po podanym id i ustawiajacy go jako obecnie edytowany trening
	
	@GetMapping("/loadSaved/{id}")
	public void loadSaved (@PathVariable int id) {
		service.loadReadyWorkoutToCurrentFromDB(id);	
	}

	//Endpoint zapisujacy obecnie edytowany trening do bazy danych gotowych treningow

	@PostMapping("/saveCurrent")
	public void saveCurrent () {
		service.saveCurrentWorkoutToDB();
	}

	//Endpoint zwracajacy gotowy trening po podanym id
	
	@GetMapping("/getWorkout/{id}")
	public @ResponseBody ReadyWorkout getReadyWorkoutFromDB (@PathVariable int id){
	  return service.getReadyWorkoutFromDB(id);
	}

	//Endpoint zapisujacy trening podany w Requescie
	
	@PostMapping("/saveWorkout")
	public @ResponseBody ReadyWorkout saveReadyWorkout (@RequestBody ReadyWorkout workoutToSave) {
		return service.saveReadyWorkoutToDB(workoutToSave);
	}

	//Endpoint zwracajacy obecnie edytowany trening jako gotowy trening
	
	@GetMapping("/getCurrent")
	public @ResponseBody ReadyWorkout getCurrent () {
		return service.getCurrentWorkoutAsReady();
	}

	//Endpoint zapisujacy na pulpicie wybrany na podstawie id trening z bazy danych
	
	@GetMapping("saveReadyWorkoutAtDesktop/{id}")
	public void saveReadyWorkoutAtDesktop(@PathVariable int id) {
		service.saveReadyWorkoutFromDBAtDesktop(id);
	}

	//Endpoint aktualizujacy tresc gotowego treniengu
	
	@PatchMapping("/updateReadyWorkout/{id}")
	public void updateReadyWorkout (@PathVariable int id, @RequestBody String freshContent) {
		service.updateWorkoutInDB(id, freshContent);
	}

	//Endpoint czyszczacy baze danych
	
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		service.deleteAllFromDB();
	}

	//Endpoint usuwajacy gotowy trening na podstawie podanego id
	
	@DeleteMapping("/delete/{id}")
	public void delete (@PathVariable int id) {
		service.deleteWorkoutFromDB(id);
	}

	//Endpoint zwracajacy wszystkie gotowe treniengi jako wylistowanego Stringa
	
	@GetMapping("/list")
	public @ResponseBody String list () {
		return service.listWorkoutsWithDates();
	}

	//Endpoint zwracajacy wsztskit gotowe treningi jako Lista
	
	@GetMapping("/getAll")
	public @ResponseBody List<ReadyWorkout> getAll() {
		return service.getAllWorkoutsObjects();
	}
}
