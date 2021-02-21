package com.revature.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.beans.ExerciseReference;
import com.revature.beans.Sets;
import com.revature.beans.User;
import com.revature.beans.Workout;
import com.revature.repositories.WorkoutRepo;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class WorkoutServiceTests {

	@MockBean
	WorkoutRepo repo;
	
	@Autowired
	WorkoutService serv;

	@Test
	void addWorkoutTest() {
		User user = new User();
		Workout wo = new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user);
		Mockito.when(repo.save(wo)).thenReturn(wo);
		
		wo = serv.addWorkout(wo);
		Assertions.assertEquals(480, wo.getDuration());
	}
	
	//Testing both get by ID and by name
	@Test
	void getWorkoutTest() {
		int id = 1;
		String name = "Rich Piana's 8-Hour Arm Workout";
		User user = new User();
		Workout wo = new Workout();
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user)));
		Mockito.when(repo.findByName(name)).thenReturn(new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user));
		//Mockito.when(repo.findByName(name)).thenReturn(Stream.of(new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user)).collect(Collectors.toList()));

		
		wo = serv.getWorkout(id);
		Assertions.assertEquals(id, wo.getWorkout_id());
		Assertions.assertEquals(600, wo.getCalories());
		
		wo = null;//reset workout object
		wo = serv.getWorkout(name);
		Assertions.assertEquals(id, wo.getWorkout_id());
		Assertions.assertEquals("Arms", wo.getmGroup());
	}
	
	@Test
	void getAllWorkoutsTest() {
		List<Workout> allWorkouts = new ArrayList<Workout>();
		Mockito.when(repo.findAll()).thenReturn(Stream.of(new Workout(), new Workout(), new Workout()).collect(Collectors.toList()));
		
		allWorkouts = serv.getAllWorkouts();
		Assertions.assertEquals(3, allWorkouts.size());
	}
	
	@Test
	void updateWorkoutTest() {
		User user = new User();
		Workout wo = new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user);
		Mockito.when(repo.save(wo)).thenReturn(wo);
		
		Assertions.assertEquals(480, wo.getDuration());		
		wo.setName("Rich Piana's 6-Hour Arm Workout");
		wo.setDuration(360);
		wo = serv.updateWorkout(wo);
		Assertions.assertEquals(360, wo.getDuration());	
	}
	
	@Test
	void deleteWorkoutTest() {
		User user = new User();
		Workout wo = new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user);
		Mockito.when(repo.findById(wo.getWorkout_id())).thenReturn(Optional.of(wo));
		Mockito.when(repo.findById(0)).thenThrow(IllegalArgumentException.class);
		
		Assertions.assertEquals(true, serv.deleteWorkout(wo.getWorkout_id()));
		verify(repo, times(1)).delete(wo);
		
		Assertions.assertEquals(false, serv.deleteWorkout(0));
	}
	
	@Test
	void getAllWorkoutsByUserIdTest() {
		int id = 2;
		
		User user1 = new User();
		user1.setUserID(1);
		
		User user2 = new User();
		user2.setUserID(2);
		
		List<Workout> allUserWorkouts = new ArrayList<Workout>();
		Mockito.when(repo.findAll()).thenReturn(Stream.of(new Workout(1, "Rich Piana's 8-Hour Arm Workout", 480, "Arms", 600, user1),
				new Workout(2, "Test2", 2, "Test", 2, user2)).collect(Collectors.toList()));
		
		allUserWorkouts = serv.getAllWorkoutsByUserId(id);
		Assertions.assertEquals(1, allUserWorkouts.size());
	}

}
