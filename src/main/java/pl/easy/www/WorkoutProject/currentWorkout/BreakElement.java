package pl.easy.www.WorkoutProject.currentWorkout;

import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;

public class BreakElement implements WorkoutPice{

	int duration;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "BreakElement [duration=" + duration + "]";
	}

	public BreakElement(int duration) {
		super();
		this.duration = duration;
	}
	
	
	
}
