package pl.easy.www.WorkoutProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;


@Entity
@Table(name="exercises")
public class Exercise implements WorkoutPice {
	
	public Exercise() {
		
	}
	
	public Exercise (String exerciseGroupName, String exerciseDirectName, String musculeGroupName, String muscleDirectName) {
		this.exerciseGroupName = exerciseGroupName;
		this.exerciseDirectName =exerciseDirectName;
		this.muscleDirectName = muscleDirectName;
		this.muscleGroupName = musculeGroupName;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "exercise_group_name")
	private String exerciseGroupName;
	
	@Column(name = "exercise_direct_name")
	private String exerciseDirectName;
	
	@Column(name = "muscle_group_name")
	private String muscleGroupName;
	
	@Column(name = "muscle_direct_name")
	private String muscleDirectName;
	
	@Transient
	private int reps;
	
	@Transient
	private int series;
	
	

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return   exerciseGroupName 
				+ " "
				+ exerciseDirectName 
				+ " " 
				+ muscleGroupName 
				+ " " 
				+ muscleDirectName
				+ "r: " 
				+reps
				+"/"
				+"s: "
				+series
				+" "  
				+"\\/";
	}


	
	
	
	
}
