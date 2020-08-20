package pl.easy.www.WorkoutProject.mappers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import pl.easy.www.WorkoutProject.entity.ReadyWorkout;

public class ReadyWorkoutMapper {
	
	
	//mapToListOfReadyWorkout
	public static List<ReadyWorkout> mapIterableToList (Iterable<ReadyWorkout> iterableCollection){
		return StreamSupport
				.stream(iterableCollection.spliterator(),false)
				.collect(Collectors.toList());
	}

}
