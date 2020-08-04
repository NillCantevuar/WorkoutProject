package pl.easy.www.WorkoutProject.repository;


import org.springframework.data.repository.CrudRepository;

import pl.easy.www.WorkoutProject.entity.ReadyWorkout;

public interface ReadyWorkoutRepository extends CrudRepository<ReadyWorkout, Integer> {
	
	

}
