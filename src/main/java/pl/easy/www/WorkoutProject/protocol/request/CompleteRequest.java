package pl.easy.www.WorkoutProject.protocol.request;

public class CompleteRequest {
	
	private ExerciseRequest exerciseRequest;
	private VolumeRequest volumeRequest;
	
	
	
	public CompleteRequest(ExerciseRequest exerciseRequest, VolumeRequest volumeRequest) {
		super();
		this.exerciseRequest = exerciseRequest;
		this.volumeRequest = volumeRequest;
	}
	public ExerciseRequest getExerciseRequest() {
		return exerciseRequest;
	}
	public void setExerciseRequest(ExerciseRequest exerciseRequest) {
		this.exerciseRequest = exerciseRequest;
	}
	public VolumeRequest getVolumeRequest() {
		return volumeRequest;
	}
	public void setVolumeRequest(VolumeRequest volumeRequest) {
		this.volumeRequest = volumeRequest;
	}
	
	
	

}
