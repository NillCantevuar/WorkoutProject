package pl.easy.www.WorkoutProject.servicesTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.easy.www.WorkoutProject.services.ReadyWorkoutService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadyWorkoutServiceTest {
	
	@Autowired
	ReadyWorkoutService service;
	
	
	// w beforte chce tworzyc zapelnione cuurrent list
	// i wyczyszczone Ready workout DB

}
