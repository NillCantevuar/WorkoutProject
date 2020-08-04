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
		return "Break: "+duration+"s\n\n";
	}

	public BreakElement(int duration) {
		super();
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BreakElement other = (BreakElement) obj;
		if (duration != other.duration)
			return false;
		return true;
	}

	@Override
	public String showInfo() {
		return String.valueOf(duration);
	}
	
	
	
	
	
}
