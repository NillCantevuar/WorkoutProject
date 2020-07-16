package pl.easy.www.WorkoutProject.mappers;

import pl.easy.www.WorkoutProject.currentWorkout.BreakElement;
import pl.easy.www.WorkoutProject.protocol.request.BreakRequest;

public class BreakMapper {

	public static BreakElement map(BreakRequest request) {
		return new BreakElement(request.getDuration());
	}
}
