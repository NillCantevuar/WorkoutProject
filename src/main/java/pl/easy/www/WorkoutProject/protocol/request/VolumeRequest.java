package pl.easy.www.WorkoutProject.protocol.request;

public class VolumeRequest {
	
	private int series;
	private int repetitions;
	
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
	public VolumeRequest(int series, int repetitions) {
		this.series = series;
		this.repetitions = repetitions;
	}
	
	

}
