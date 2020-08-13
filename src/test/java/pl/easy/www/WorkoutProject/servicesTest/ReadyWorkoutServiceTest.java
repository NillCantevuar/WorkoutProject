package pl.easy.www.WorkoutProject.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadyWorkoutServiceTest extends CurrentWorkoutAbility{
	
	@Autowired
	ReadyWorkoutService service;
	
	@Autowired
	CurrentWorkoutService currentService;
	
	
	@Before
	public void prepereForTests(){
		CurrentWorkout.workout.clear();
		service.deleteAllFromDB();
	}
	
	@Test
	public void should_give_list_of_saved_workouts_equals_size() {
		//given
		prepereNotEmptyList();
		service.saveCurrentWorkoutToDB();
		service.saveCurrentWorkoutToDB();
		service.saveCurrentWorkoutToDB();
		//when
		List<ReadyWorkout> resultList = service.getAllWorkoutsObjects();
		//then
		assertEquals(3, resultList.size());
	}
	
	@Test
	public void should_save_current_workout_to_db () {
		//given
		prepereNotEmptyList();
		
		//when
		service.saveCurrentWorkoutToDB();
		//then
		List<ReadyWorkout> readyWorkouts = service.getAllWorkoutsObjects();
		assertEquals(currentService.saveWorkout(), readyWorkouts.get(0).getContent()); 
	}
	
	@Test
	public void should_get_single_readyWorkout_using_id () {
		//given
		prepereNotEmptyList();
		String expectedContent = currentService.saveWorkout();
		ReadyWorkout savedReadyWorkout = service.saveCurrentWorkoutToDB();
		//when
		ReadyWorkout pulledReadyWorkout =  service.getReadyWorkoutFromDB(savedReadyWorkout.getId());
		//then
		assertEquals(expectedContent,pulledReadyWorkout.getContent() );
		
	}
	@Test
	public void should_delete_all_from_db () {
		//given
		prepereNotEmptyList();
		service.saveCurrentWorkoutToDB();
		service.saveCurrentWorkoutToDB();
		service.saveCurrentWorkoutToDB();
		//when
		service.deleteAllFromDB();
		//then
		List<ReadyWorkout> readyWorkouts = service.getAllWorkoutsObjects();
		assertEquals(readyWorkouts.size(), 0);
	}
	
	@Test
	public void should_delete_readyWorkout_from_db_using_id () {
		//given
		prepereNotEmptyList();
		ReadyWorkout savedReadyWorkut = service.saveCurrentWorkoutToDB();
		//when
		service.deleteWorkoutFromDB(savedReadyWorkut.getId());
		//then
		assertThrows(NoSuchElementException.class,
		() -> service.getReadyWorkoutFromDB(savedReadyWorkut.getId()));
		
	}
	
	@Test
	public void should_update_content_at_given_id() {
		//given
		prepereNotEmptyList();
		ReadyWorkout savedOldReadyWorkut = service.saveCurrentWorkoutToDB();
		CurrentWorkout.workout.clear();
		String freshContent = currentService.saveWorkout();
		//when
		service.updateWorkoutInDB(savedOldReadyWorkut.getId(), freshContent);
		//then
		List<ReadyWorkout> readyWorkouts = service.getAllWorkoutsObjects();
		assertEquals(freshContent, readyWorkouts.get(0).getContent());
		
		
	}

	@Ignore
	@Test
	public void check_list_ready_workouts () {
		//given
		prepereNotEmptyList();
		service.saveCurrentWorkoutToDB();
		currentService.clearCurrentWorkout();
		prepereNotEmptyListV2();
		service.saveCurrentWorkoutToDB();
		currentService.clearCurrentWorkout();
		prepereNotEmptyListV3();
		service.saveCurrentWorkoutToDB();
		currentService.clearCurrentWorkout();
		//when
		String result =service.listWorkoutsWithDates();
		//then
		assertEquals("1. 2020-08-06T09:43:32.503\r\n" + 
				   	 "2. 2020-08-06T09:43:32.510\r\n" + 
					 "3. 2020-08-06T09:43:32.512",result);	
	}
	
	@Ignore
	@Test
	public void check_save_ready_workout_from_DB_at_desktop() {
		//given
		prepereNotEmptyList();
		ReadyWorkout savedReadyWorkout = service.saveCurrentWorkoutToDB();
		//when
		service.saveReadyWorkoutFromDBAtDesktop(savedReadyWorkout.getId());
		//then
		
	}
	
	@Test
	public void should_load_ReadyWorkout_from_db_to_current () {
		//given
		prepereNotEmptyList();
		ReadyWorkout savedReadyWorkout = service.saveCurrentWorkoutToDB();
		List<WorkoutPice> expectedList =new ArrayList<>(currentService.getList()); 
		currentService.clearCurrentWorkout();
		//when
		service.loadReadyWorkoutToCurrent(savedReadyWorkout.getId());
		//then
		assertEquals(currentService.getList(), expectedList);

	}
	
	

}
