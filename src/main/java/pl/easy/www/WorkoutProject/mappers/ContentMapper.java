package pl.easy.www.WorkoutProject.mappers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.currentWorkout.ExerciseElement;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;

public class ContentMapper {
	
	
	public static List<WorkoutPice> decodeContetn(String content){
		List<WorkoutPice> decodedWorkout = new ArrayList<WorkoutPice>();
		String[] singleLines= content.split("\n");
		System.out.println(singleLines[0]);
		System.out.println(singleLines[1]);
		System.out.println(singleLines[2]);
		System.out.println(singleLines[3]);
		System.out.println(singleLines[4]);
		System.out.println(singleLines[5]);
		
		for (int i = 0; i < singleLines.length; i++) {
			if(singleLines[i].equals("")) {
			}else if(singleLines[i].charAt(0)=='B') {
				createBreakElementFromLine(singleLines[i]);
			}else if(singleLines[i].charAt(0)=='E') {
				createExerciseElementFromLines(concatStrings(
						singleLines[i],
						singleLines[i+1],
						singleLines[i+2]));
				i+=2;
			}else {
				
			}
		}
		
		return decodedWorkout;
	}
	
	
	
	
	
	
	
	public static BreakElement createBreakElementFromLine(String breakLine) {
		Pattern pattern = Pattern.compile("(Break -- )(\\d*)s");
		Matcher matcher = pattern.matcher(breakLine);
		matcher.matches();
		return new BreakElement(Integer.valueOf((matcher.group(2))));
	}
	
	public static ExerciseElement createExerciseElementFromLines(String exerciseLines) {
		
		Pattern pattern = Pattern.compile("E:(.*) \\| (.*) Target: (.*) \\| (.*) Series: ([\\d]*) Reps: ([\\d]*)");
		Matcher matcher = pattern.matcher(exerciseLines);
		System.out.println(matcher.matches());
		
		 return new ExerciseElement(
				 matcher.group(1),
				 matcher.group(2),
				 matcher.group(3),
				 matcher.group(4),
				 Integer.valueOf(matcher.group(5)),
				 Integer.valueOf(matcher.group(6)));
		
	}
	//metoda ktora zlaczy mi trzy stingi usuwajac new line
	
	private static String concatStrings(String first,String second, String third) {
		return first+second+third;
	}

}
