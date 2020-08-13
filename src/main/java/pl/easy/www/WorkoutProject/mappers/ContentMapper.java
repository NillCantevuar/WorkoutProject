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
				decodedWorkout.add( createBreakElementFromLine(singleLines[i]));
			}else if(singleLines[i].charAt(0)=='E') {
				decodedWorkout.add(createExerciseElementFromLines(concatStrings(
						singleLines[i],
						singleLines[i+1],
						singleLines[i+2])));
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
		int duration = Integer.valueOf((matcher.group(2)));
		matcher.reset();
		return new BreakElement(duration);
	}
	
	public static ExerciseElement createExerciseElementFromLines(String exerciseLines) {
		
		Pattern pattern = Pattern.compile("E:(.*) \\| (.*) Target: (.*) \\| (.*) Series: ([\\d]*) Reps: ([\\d]*)");
		Matcher matcher = pattern.matcher(exerciseLines);
		matcher.matches();
		String eg =  matcher.group(1);
		String ed =  matcher.group(2);
		String mg =  matcher.group(3);
		String md =  matcher.group(4);
		int ser = Integer.valueOf(matcher.group(5));
		int rep = Integer.valueOf(matcher.group(6));
		matcher.reset();
		 return new ExerciseElement(eg,ed,mg,md,ser,rep);
				 
	}
	//metoda ktora zlaczy mi trzy stingi usuwajac new line
	
	private static String concatStrings(String first,String second, String third) {
		return first+" "+second+" "+third;
	}

}
