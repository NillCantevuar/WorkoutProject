package pl.easy.www.WorkoutProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.protocol.request.ExerciseRequest;


@Entity
@Table(name="exercises")
public class Exercise implements WorkoutPice {
	
	
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
	
	public Exercise() {
		
	}
	
	public Exercise (String exerciseGroupName, String exerciseDirectName, String musculeGroupName, String muscleDirectName) {
		this.exerciseGroupName = exerciseGroupName;
		this.exerciseDirectName =exerciseDirectName;
		this.muscleDirectName = muscleDirectName;
		this.muscleGroupName = musculeGroupName;
		
	}
	
	public Exercise (ExerciseRequest request) {
		this.exerciseGroupName = request.getExerciseGroupName();
		this.exerciseDirectName =request.getExerciseDirectName();
		this.muscleDirectName = request.getMuscleDirectName();
		this.muscleGroupName = request.getMuscleGroupName();
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
				+ muscleDirectName;

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
		
		return true;
	}

	@Override
	public String showInfo() {
		
		return exerciseGroupName+
		exerciseDirectName+
		muscleDirectName
		+muscleGroupName;
		
	}
	
	


	
	
	
	
}
