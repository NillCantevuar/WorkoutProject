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
		id = null;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exerciseDirectName == null) ? 0 : exerciseDirectName.hashCode());
		result = prime * result + ((exerciseGroupName == null) ? 0 : exerciseGroupName.hashCode());
		result = prime * result + id;
		result = prime * result + ((muscleDirectName == null) ? 0 : muscleDirectName.hashCode());
		result = prime * result + ((muscleGroupName == null) ? 0 : muscleGroupName.hashCode());
		result = prime * result + reps;
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
		Exercise other = (Exercise) obj;
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
		if (id != other.id)
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
		if (reps != other.reps)
			return false;
		if (series != other.series)
			return false;
		return true;
	}
	
	


	
	
	
	
}
