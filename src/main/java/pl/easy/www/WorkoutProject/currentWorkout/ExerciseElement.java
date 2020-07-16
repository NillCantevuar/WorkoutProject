package pl.easy.www.WorkoutProject.currentWorkout;

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
		return "ExerciseElement [exerciseGroupName=" + exerciseGroupName + ", exerciseDirectName=" + exerciseDirectName
				+ ", muscleGroupName=" + muscleGroupName + ", muscleDirectName=" + muscleDirectName + ", series="
				+ series + ", repetitions=" + repetitions + "]";
	}
	
	

}
