package pl.easy.www.WorkoutProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.easy.www.WorkoutProject.entity.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
	
	@Override
	List<Exercise> findAll();

}
