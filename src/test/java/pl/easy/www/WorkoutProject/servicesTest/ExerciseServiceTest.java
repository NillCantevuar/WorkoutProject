package pl.easy.www.WorkoutProject.servicesTest;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;
import pl.easy.www.WorkoutProject.services.ExerciseService;
import pl.easy.www.WorkoutProject.support.ExerciseAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseServiceTest extends ExerciseAbility{
	
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Before
	public void clearDB () {
		exerciseService.clearDB();
	}
	
	@Test 
	public void should_add_to_empty_exercise_equal_size () {
		// given
		Exercise exercise = generateExercise();
		int size = 1;
		// when
		exerciseService.add(exercise);
		//then
		Assert.assertEquals(size, exerciseService.getAllExercises().size());
		
	}
	
	@Test 
	public void should_add_to_not_empty_exercise_equal_size () {
		// given
		addThreeExercisesToDB();
		Exercise exercise = generateExercise();
		int size = 4;
		// when
		exerciseService.add(exercise);
		//then
		Assert.assertEquals(size, exerciseService.getAllExercises().size());
	}
	
	@Test
	public void shold_add_exercise_and_have_equal_exercises () {
		
		// given
		Exercise exercise = generateExercise(); 
		// when
		Exercise added = exerciseService.add(exercise);
		//then
		Assert.assertEquals(exercise, exerciseService.giveById(added.getId()));
		
	}
	
	@Test
	public void shold_add_three_exercises_and_have_equal_list () {
		
		// given
		List<Exercise> exercises = generateThreeExercises();
		// when
		 exerciseService.add(exercises.get(0));
		 exerciseService.add(exercises.get(1));
		 exerciseService.add(exercises.get(2));
		//then
		Assert.assertEquals(exercises, exerciseService.getAllExercises());
		
	}
	
	@Test
	public void should_try_delete_not_existing_id () {
		//given
		int id = 1;
		//when
		//then
		Assert.assertThrows(EmptyResultDataAccessException.class, () ->{ exerciseService.delete(id);});
	}
	
	@Test
	public void should_add_and_remove_one_size_and_contains () {
		//given
		Exercise exercise = generateExercise();
		exerciseService.add(exercise);
		
		//when
		exerciseService.delete(exercise);
		//then	
		Assert.assertEquals(0, exerciseService.getAllExercises().size());
	     List<Exercise> exercises  = exerciseService.getAllExercises();
	     
	     Assert.assertNotEquals(1, exercises.stream()
	    			.filter(e ->e.getId()==exercise.getId())
	    			.collect(Collectors.toList()).size());	
	}
	
	@Test
	public void should_delete_two_of_three_exercises_by_object_check_size_and_contains() {
		//given
		List<Exercise> addedExercises = addThreeExercisesToDB();
		//when
		exerciseService.delete(addedExercises.get(0));
		exerciseService.delete(addedExercises.get(2));
		//then
		Assert.assertEquals(1, exerciseService.getAllExercises().size());
		
		List<Exercise> exercises  = exerciseService.getAllExercises();
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(0).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(2).getId())
    			.collect(Collectors.toList()).size());	
		
		
	}
	
	@Test
	public void should_delete_one_of_three_exercises_by_object_check_size_and_contains() {
		//given
		List<Exercise> addedExercises = addThreeExercisesToDB();
		//when
		exerciseService.delete(addedExercises.get(1));
		//then
		Assert.assertEquals(2, exerciseService.getAllExercises().size());
		
		List<Exercise> exercises  = exerciseService.getAllExercises();
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(1).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(0).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(2).getId())
    			.collect(Collectors.toList()).size());		
	}
	
	@Test
	public void should_add_and_remove_one_by_id_size_and_contains () {
		//given
		Exercise exercise = generateExercise();
		exerciseService.add(exercise);
		//when
		exerciseService.delete(exercise.getId());
		//then	
		
		Assert.assertEquals(0, exerciseService.getAllExercises().size());
	     List<Exercise> exercises  = exerciseService.getAllExercises();
	     
	     Assert.assertNotEquals(1, exercises.stream()
	    			.filter(e ->e.getId()==exercise.getId())
	    			.collect(Collectors.toList()).size());	
	}

	@Test
	public void should_delete_two_of_three_exercises_by_id_check_size_and_contains() {
		//given
		List<Exercise> addedExercises = addThreeExercisesToDB();
		//when
		exerciseService.delete(addedExercises.get(0).getId());
		exerciseService.delete(addedExercises.get(2).getId());
		//then
		Assert.assertEquals(1, exerciseService.getAllExercises().size());
		
		List<Exercise> exercises  = exerciseService.getAllExercises();
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(0).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(2).getId())
    			.collect(Collectors.toList()).size());		
	}
	
	@Test
	public void should_delete_one_of_three_exercises_by_id_check_size_and_contains() {
		//given
		List<Exercise> addedExercises = addThreeExercisesToDB();
		//when
		exerciseService.delete(addedExercises.get(1).getId());
		//then
		Assert.assertEquals(2, exerciseService.getAllExercises().size());
		
		List<Exercise> exercises  = exerciseService.getAllExercises();
		
		Assert.assertNotEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(1).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(0).getId())
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, exercises.stream()
    			.filter(e ->e.getId()==addedExercises.get(2).getId())
    			.collect(Collectors.toList()).size());
	}
			
	@Test
	public void should_update_one_exercise_check_contains_check_not_contains_old_check_size() {
		//given
		Exercise exercise1 = new Exercise("Podciaganie","Nachwytem","Plecy","Szerokie");
		Exercise exercise2 = new Exercise("Pompki","Zwykle","Klatka","Cala");
		
		Exercise addedExercise =  exerciseService.add(exercise1);
		//when
		Exercise updatedExercise =  exerciseService.update(exercise2, addedExercise.getId());
		//then
		List<Exercise> allExercises = exerciseService.getAllExercises();
		
		Assert.assertEquals(1, allExercises.size());
		
		Assert.assertNotEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(addedExercise.getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, allExercises.stream()
				.filter(e ->e.getExerciseDirectName().equals(updatedExercise.getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
	}		
	
	@Test
	public void should_update_one_exercise_when_three_are_in_check_contains_check_not_contains_old_check_size() {
		//given
		List<Exercise> added = addThreeExercisesToDB();
		Exercise updator = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		
		//when
		Exercise updatedExercise =  exerciseService.update(updator, added.get(1).getId());
		//then
		List<Exercise> allExercises = exerciseService.getAllExercises();
		
		Assert.assertEquals(3, allExercises.size());
		
		Assert.assertNotEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(added.get(1).getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, allExercises.stream()
				.filter(e ->e.getExerciseDirectName().equals(updator.getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
	}
	
	@Test
	public void should_update_one_exercise_when_three_are_in_check_others_behaviour() {
		//given
		List<Exercise> added = addThreeExercisesToDB();
		Exercise updator = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		
		//when
		Exercise updatedExercise =  exerciseService.update(updator, added.get(1).getId());
		//then
		List<Exercise> allExercises = exerciseService.getAllExercises();
				
		Assert.assertEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(added.get(0).getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		

		Assert.assertEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(added.get(2).getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
				
	}
			
	@Test
	public void should_update_two_exercise_when_three_are_in_check_contains_check_not_contains_old_check_size() {
		//given
		List<Exercise> added = addThreeExercisesToDB();
		Exercise updator1 = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		Exercise updator2 = new Exercise("Przysiady","Na Jednej Nodze", "Nogi", "Uda");
		
		//when
		Exercise updatedExercise1 =  exerciseService.update(updator1, added.get(0).getId());
		Exercise updatedExercise2 =  exerciseService.update(updator2, added.get(2).getId());
		//then
		List<Exercise> allExercises = exerciseService.getAllExercises();
		
		Assert.assertEquals(3, allExercises.size());
		
		Assert.assertNotEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(added.get(0).getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
		Assert.assertNotEquals(1, allExercises.stream()
    			.filter(e ->e.getExerciseDirectName().equals(added.get(2).getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, allExercises.stream()
				.filter(e ->e.getExerciseDirectName().equals(updator1.getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
		Assert.assertEquals(1, allExercises.stream()
				.filter(e ->e.getExerciseDirectName().equals(updator2.getExerciseDirectName()))
    			.collect(Collectors.toList()).size());
		
	}
	
	@Test
	public void should_update_two_exercise_when_three_are_in_check_others_behaviour() {
		//given
		List<Exercise> added = addThreeExercisesToDB();
		Exercise updator1 = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		Exercise updator2 = new Exercise("Przysiady","Na Jednej Nodze", "Nogi", "Uda");
		//when
		Exercise updatedExercise1 =  exerciseService.update(updator1, added.get(0).getId());
		Exercise updatedExercise2 =  exerciseService.update(updator2, added.get(2).getId());
		//then
		List<Exercise> allExercises = exerciseService.getAllExercises();
				
		Assert.assertEquals(1, allExercises.stream()
		   		.filter(e ->e.getExerciseDirectName().equals(added.get(1).getExerciseDirectName()))
		   		.collect(Collectors.toList()).size());
	}
	
	@Test
	public void should_try_update_emty_db_throw_expected () {
		//given
		Exercise updator1 = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		//when
		//then
		Assert.assertThrows(NoSuchElementException.class, () -> {
				exerciseService.update(updator1, 0);
			});
	}
	
	@Test
	public void should_update_at_same_id_twice () {
		//given
		Exercise added = addOneExercise();
		Exercise updator1 = new Exercise("Wycieraczki", "Na Drazku", "Brzuch", "Skosy");
		Exercise updator2 = new Exercise("Przysiady","Na Jednej Nodze", "Nogi", "Uda");
		//when
		Exercise firstUpdated = exerciseService.update(updator1, added.getId());
		exerciseService.update(updator2, firstUpdated.getId());
		List<Exercise> allExercises = exerciseService.getAllExercises();
		//then
		
		//nie rowna sie pierwszemy
		Assert.assertNotEquals(1, allExercises.stream()
		   		.filter(e ->e.getExerciseDirectName().equals(added.getExerciseDirectName()))
		   		.collect(Collectors.toList()).size());

		//nie rowna sie drugiemu
		Assert.assertNotEquals(1, allExercises.stream()
				.filter(e ->e.getExerciseDirectName()
						.equals(updator1.getExerciseDirectName()))
				.collect(Collectors.toList()).size());
		//rowana sie trzeciemu
		Assert.assertEquals(1, allExercises.stream()
				.filter(e->e.getExerciseDirectName()
						.equals(updator2.getExerciseDirectName()))
				.collect(Collectors.toList()).size());
	}
			
		
	
	
	

}
