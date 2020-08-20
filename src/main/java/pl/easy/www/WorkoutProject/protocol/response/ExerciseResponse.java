package pl.easy.www.WorkoutProject.protocol.response;

import org.springframework.hateoas.RepresentationModel;


public class ExerciseResponse extends RepresentationModel<ExerciseResponse> {
	
	private int exerciseId;
	private String exerciseGroupName;
	private String exerciseDirectName;
	private String muscleGroupName;
	private String muscleDirectName;
	
	public ExerciseResponse(int exerciseId, String exerciseGroupName, String exerciseDirectName, String muscleGroupName,
			String muscleDirectName) {
		this.exerciseId = exerciseId;
		this.exerciseGroupName = exerciseGroupName;
		this.exerciseDirectName = exerciseDirectName;
		this.muscleGroupName = muscleGroupName;
		this.muscleDirectName = muscleDirectName;
	}
	
	public String getExerciseGroupName() {
		return exerciseGroupName;
	}
	public int getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
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
	public void setExerciseGroupName(String exerciseGroupName) {
		this.exerciseGroupName = exerciseGroupName;
	}
	
	
}
