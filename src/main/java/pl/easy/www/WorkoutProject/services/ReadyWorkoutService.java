package pl.easy.www.WorkoutProject.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.easy.www.WorkoutProject.entity.ReadyWorkout;
import pl.easy.www.WorkoutProject.interfaces.WorkoutPice;
import pl.easy.www.WorkoutProject.mappers.ContentMapper;
import pl.easy.www.WorkoutProject.mappers.ReadyWorkoutMapper;
import pl.easy.www.WorkoutProject.repository.ReadyWorkoutRepository;

@Service
public class ReadyWorkoutService {
	
	@Autowired
	ReadyWorkoutRepository repository; 
	
	@Autowired
	CurrentWorkoutService currentWorkoutService;
	
	public ReadyWorkout saveCurrentWorkoutToDB () {	
		return repository.save(new ReadyWorkout(extractContentFromCurrentWorkout()));
	}
	
	public ReadyWorkout getReadyWorkoutFromDB(int id) { 
		return repository.findById(id).get();
	}
	
	public List<ReadyWorkout> getAllWorkoutsObjects(){
		 return ReadyWorkoutMapper.mapIterableToList(repository.findAll());
	}
	
	public String listWorkoutsWithDates() {
		String listedElements=""; 
		for (ReadyWorkout rW : repository.findAll()) {	
			listedElements += rW.getId()+". "+rW.getCreationDate()+"\n";
		}
		return listedElements;
	}
	
	public void deleteWorkoutFromDB(int id) {
		repository.deleteById(id);
	}
	
	public void deleteAllFromDB() {
		repository.deleteAll();
	}
	
	public ReadyWorkout updateWorkoutInDB (int idToUpdate,String content) {
		 ReadyWorkout workoutToUpdate = repository.findById(idToUpdate).get();
		 workoutToUpdate.setContent(content);
		 return repository.save(workoutToUpdate);
	}
	
	private String extractContentFromCurrentWorkout() {
		return currentWorkoutService.saveWorkout();
		
	}
	 
	public void saveReadyWorkoutFromDBAtDesktop(int id) {
		ReadyWorkout toSave =  getReadyWorkoutFromDB(id);
		currentWorkoutService.saveAtDesktop(toSave.getContent());
		
	}

	public void loadReadyWorkoutToCurrent(int id) {
		ReadyWorkout loadedWorkout = getReadyWorkoutFromDB(id);
		List<WorkoutPice> tempList =ContentMapper.decodeContetn(loadedWorkout.getContent());
		currentWorkoutService.overvriteCurrentWorkout(tempList);
		
	}
	public String loadFileFromDiscToContetnString(String filePath) {
		String content ="";
		Path path = Paths.get(filePath);
		File myFile = path.toFile();
		try {
			Scanner scanner = new Scanner(myFile);
			while (scanner.hasNextLine()) {
				content += scanner.nextLine()+"\n";
			}
			scanner.close();
			}catch (FileNotFoundException e) {
			System.out.println("Blad czytania pliku");
			e.printStackTrace();
		}
	
		
		return content;
	}

}
