package pl.easy.www.WorkoutProject.currentWorkout;

import pl.easy.www.WorkoutProject.entity.Exercise;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;

public class ExerciseElement implements WorkoutPice{
	
	private String exerciseGroupName;
	private String exerciseDirectName;
	private String muscleGroupName;
	private String muscleDirectName;
	private int series;
	private int repetitions;
	
	
	
	public ExerciseElement(String exerciseGroupName, String exerciseDirectName, String muscleGroupName,
			String muscleDirectName) {
		super();
		this.exerciseGroupName = exerciseGroupName;
		this.exerciseDirectName = exerciseDirectName;
		this.muscleGroupName = muscleGroupName;
		this.muscleDirectName = muscleDirectName;
	}
	public ExerciseElement(String exerciseGroupName, String exerciseDirectName, String muscleGroupName,
			String muscleDirectName, int series, int repetitions) {
		
		this.exerciseGroupName = exerciseGroupName;
		this.exerciseDirectName = exerciseDirectName;
		this.muscleGroupName = muscleGroupName;
		this.muscleDirectName = muscleDirectName;
		this.series = series;
		this.repetitions = repetitions;
	}
	public ExerciseElement(Exercise exercise, int series, int repetitions) {
		
		this.exerciseGroupName = exercise.getExerciseGroupName();
		this.exerciseDirectName = exercise.getExerciseDirectName();
		this.muscleGroupName = exercise.getMuscleGroupName();
		this.muscleDirectName = exercise.getMuscleDirectName();
		this.series = series;
		this.repetitions = repetitions;
	}
	
	public String getExerciseGroupName() {
		return exerciseGroupName;
	}
	public void setExerciseGroupName(String exerciseGroupName) {
		this.exerciseGroupName = exerciseGroupName;
	}
	public String getExerciseDirectName() {
		return exerciseDirectName;
	}
	public void setExerciseDirectName(String exerciseDirectName) {
		this.exerciseDirectName = exerciseDirectName;
	}
	public String getMuscleGroupName() {
		return muscleGroupName;
	}
	public void setMuscleGroupName(String muscleGroupName) {
		this.muscleGroupName = muscleGroupName;
	}
	public String getMuscleDirectName() {
		return muscleDirectName;
	}
	public void setMuscleDirectName(String muscleDirectName) {
		this.muscleDirectName = muscleDirectName;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	
	@Override
	public String toString() {
		return "//"+exerciseGroupName + " " + exerciseDirectName+"\n"+
				"Target: "+ muscleGroupName +" "+ muscleDirectName + "\n"+ 
				"Series: "+ series + " Reps: " + repetitions+"\n\n";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exerciseDirectName == null) ? 0 : exerciseDirectName.hashCode());
		result = prime * result + ((exerciseGroupName == null) ? 0 : exerciseGroupName.hashCode());
		result = prime * result + ((muscleDirectName == null) ? 0 : muscleDirectName.hashCode());
		result = prime * result + ((muscleGroupName == null) ? 0 : muscleGroupName.hashCode());
		result = prime * result + repetitions;
		result = prime * result + series;
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
		ExerciseElement other = (ExerciseElement) obj;
		if (exerciseDirectName == null) {
			if (other.exerciseDirectName != null)
				return false;
		} else if (!exerciseDirectName.equals(other.exerciseDirectName))
			return false;
		if (exerciseGroupName == null) {
			if (other.exerciseGroupName != null)
				return false;
		} else if (!exerciseGroupName.equals(other.exerciseGroupName))
			return false;
		if (muscleDirectName == null) {
			if (other.muscleDirectName != null)
				return false;
		} else if (!muscleDirectName.equals(other.muscleDirectName))
			return false;
		if (muscleGroupName == null) {
			if (other.muscleGroupName != null)
				return false;
		} else if (!muscleGroupName.equals(other.muscleGroupName))
			return false;
		if (repetitions != other.repetitions)
			return false;
		if (series != other.series)
			return false;
		return true;
	}
	@Override
	public String getInfo() {
			
		return exerciseGroupName+
		exerciseDirectName+
		muscleGroupName+
		muscleDirectName+
		series+
		repetitions;
	}
	
	
	
	

}
