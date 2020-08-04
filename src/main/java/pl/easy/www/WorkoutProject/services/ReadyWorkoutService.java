package pl.easy.www.WorkoutProject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.mappers.ReadyWorkoutMapper;
import pl.easy.www.WorkoutProject.repository.ReadyWorkoutRepository;

public class ReadyWorkoutService {
	
	@Autowired
	ReadyWorkoutRepository repository;
	
	@Autowired
	CurrentWorkoutService currentWorkoutService;
	
	public ReadyWorkout saveCurrentWorkoutToDB () {	
		return repository.save(new ReadyWorkout(extractContentFromCurrentWorkout()));
	}
	
	public ReadyWorkout getReadyWorkoutFromDB(int id) {
		return repository.findById(id).get();
	}
	
	public List<ReadyWorkout> getAllWorkoutsObjects(){
		 return ReadyWorkoutMapper.mapIterableToList(repository.findAll());
	}
	
	public void deleteWorkoutFromDB(int id) {
		repository.deleteById(id);
	}
	
	public void deleteAllFromDB() {
		repository.deleteAll();
	}
	
	public ReadyWorkout updateWorkoutInDB (int idToUpdate,String content) {
		 ReadyWorkout workoutToUpdate = repository.findById(idToUpdate).get();
		 workoutToUpdate.setContent(content);
		 return repository.save(workoutToUpdate);
		
		
	}
	
	
	
	private String extractContentFromCurrentWorkout() {
		return currentWorkoutService.saveWorkout();
		
	}

}
