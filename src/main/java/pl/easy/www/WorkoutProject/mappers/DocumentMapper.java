package pl.easy.www.WorkoutProject.mappers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;

public class DocumentMapper {
	
	
	
	
	
	public static BreakElement createBreakElementFromLine(String breakLine) {
		Pattern pattern = Pattern.compile("(Break -- )(\\d*)s");
		Matcher matcher = pattern.matcher(breakLine);
		matcher.matches();
		return new BreakElement(Integer.valueOf((matcher.group(2))));
	}
	
	public static ExerciseElement createExerciseElementFromLines(String exerciseLines) {
		Pattern pattern = Pattern.compile("");
		Matcher matcher = pattern.matcher(exerciseLines);
		matcher.matches();
		String egr ;
		String edi ;
		String mgr ;
		String mdi ;
		int ser ;
		int reps;
		
		//ExerciseElement exerciseElement = new ExerciseElement("")
		return null;
	}

}
