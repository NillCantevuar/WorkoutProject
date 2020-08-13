package pl.easy.www.WorkoutProject.MappersTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.CurrentWorkout;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.mappers.ContentMapper;
import pl.easy.www.WorkoutProject.services.CurrentWorkoutService;
import pl.easy.www.WorkoutProject.support.CurrentWorkoutAbility;

@SpringBootTest
public class ContentMapperTest extends CurrentWorkoutAbility{
	
	@Before
	public void clearAll() {
		CurrentWorkout.workout = new ArrayList<WorkoutPice>();
	}
	
	
	@Test
	public void should_translate_text_line_to_BreakElement() {
		//given
		String breakLine = "Break -- 30s";
		BreakElement expectedBreakElement = new BreakElement(30);
		//when
		BreakElement resoultBreakElement = ContentMapper.createBreakElementFromLine(breakLine); 
		//then
		assertEquals(expectedBreakElement, resoultBreakElement);
	}
	
	@Test
	public void should_translate_text_lines_to_ExerciseElement() {
		//given
		String exerciseLine = "E:Dipy | Na drazku Target: rece | triceps Series: 3 Reps: 10";						 
		ExerciseElement expectedExerciseElement = 
				new ExerciseElement("Dipy","Na drazku","rece","triceps",3,10);
		//when
		ExerciseElement resoultExerciseElement = ContentMapper.createExerciseElementFromLines(exerciseLine);
		//then
		assertEquals(expectedExerciseElement, resoultExerciseElement);
	}
	
	@Test
	public void should_decode_content_string_to_List_of_WorkoutPice() {
		//given
		prepereNotEmptyList();
		CurrentWorkoutService service = new CurrentWorkoutService();
		String content =  service.saveWorkout();
		List<WorkoutPice> expectedList = new ArrayList<WorkoutPice>(CurrentWorkout.workout);
		//when
		List<WorkoutPice> resultList = ContentMapper.decodeContetn(content);
		//then
		assertEquals(expectedList, resultList);
	}
}
