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

import com.revature.beans.GoalsBodyWeight;
import com.revature.beans.User;
//import com.revature.beans.ExerciseReference;
import com.revature.repositories.GoalsBodyWeightRepo;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class GoalsBodyWeightServiceTests {

	@MockBean
	GoalsBodyWeightRepo gbwr;
	
	@Autowired
	GoalsBodyWeightService gbws;
	
	
	@Test
	void addGoalTest() {
		User user = new User();
		GoalsBodyWeight goal = new GoalsBodyWeight(1, 143, 150, user);
		Mockito.when(gbwr.save(goal)).thenReturn(goal);
		
		goal = gbws.addGoal(goal);
		Assertions.assertEquals(143, goal.getCurrent_weight());
	}
	
	
	@Test
	void getGoalTest() {
		int id = 1;
		User user = new User();
		GoalsBodyWeight goal = new GoalsBodyWeight();
		Mockito.when(gbwr.findById(id)).thenReturn(Optional.of(new GoalsBodyWeight(1, 143, 150, user)));
		
		goal = gbws.getGoal(id);
		Assertions.assertEquals(id, goal.getBodyweight_id());
		Assertions.assertEquals(150, goal.getGoal_weight());	
	}
	
	@Test
	void getAllGoalsTest() {
		List<GoalsBodyWeight> allGoals = new ArrayList<GoalsBodyWeight>();
		Mockito.when(gbwr.findAll()).thenReturn(Stream.of(new GoalsBodyWeight(), new GoalsBodyWeight()).collect(Collectors.toList()));
		
		allGoals = gbws.getAllGoals();
		Assertions.assertEquals(2, allGoals.size());
	}
	
	@Test
	void updateGoalTest() {
		User user = new User();
		GoalsBodyWeight goal = new GoalsBodyWeight(1, 143, 150, user);
		Mockito.when(gbwr.save(goal)).thenReturn(goal);
		
		Assertions.assertEquals(143, goal.getCurrent_weight());		
		goal.setCurrent_weight(149);
		goal = gbws.updateGoal(goal);
		Assertions.assertEquals(149, goal.getCurrent_weight());	
	}
	
	@Test
	void deleteGoalTest() {
		User user = new User();
		GoalsBodyWeight goal = new GoalsBodyWeight(1, 143, 150, user);
		Mockito.when(gbwr.findById(goal.getBodyweight_id())).thenReturn(Optional.of(goal));
		Mockito.when(gbwr.findById(0)).thenThrow(IllegalArgumentException.class);
		
		Assertions.assertEquals(true, gbws.deleteGoal(goal.getBodyweight_id()));
		verify(gbwr, times(1)).delete(goal);
		
		Assertions.assertEquals(false, gbws.deleteGoal(0));
	}
	
	
}
