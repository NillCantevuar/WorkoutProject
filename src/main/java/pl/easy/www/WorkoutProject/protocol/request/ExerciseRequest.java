package pl.easy.www.WorkoutProject.protocol.request;

import pl.easy.www.WorkoutProject.entity.Exercise;

public class ExerciseRequest {
	
	private String exerciseGroupName;
	private String exerciseDirectName;
	private String muscleGroupName;
	private String muscleDirectName;
	
	public ExerciseRequest(Exercise exercise) {
		this.exerciseGroupName = exercise.getExerciseGroupName();
		this.exerciseDirectName = exercise.getExerciseDirectName();
		this.muscleGroupName = exercise.getMuscleGroupName();
		this.muscleDirectName = exercise.getMuscleDirectName();
	}
	public ExerciseRequest() {
	
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
	
	
	
	

}
