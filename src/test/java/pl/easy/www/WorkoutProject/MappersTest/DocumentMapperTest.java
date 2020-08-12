package pl.easy.www.WorkoutProject.MappersTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.mappers.DocumentMapper;

@SpringBootTest
public class DocumentMapperTest {
	
	@Test
	public void should_translate_text_line_to_BreakElement() {
		//given
		String breakLine = "Break -- 30s";
		BreakElement expectedBreakElement = new BreakElement(30);
		//when
		BreakElement resoultBreakElement = DocumentMapper.createBreakElementFromLine(breakLine);
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
		ExerciseElement resoultExerciseElement = DocumentMapper.createExerciseElementFromLines(exerciseLine);
		//then
		assertEquals(expectedExerciseElement, resoultExerciseElement);
	
	}
	

}
