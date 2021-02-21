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
import com.revature.beans.GoalsBodyWeight;
import com.revature.beans.Sets;
import com.revature.beans.User;
import com.revature.beans.Workout;
import com.revature.repositories.SetsRepo;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class SetsServiceTests {
	
	@MockBean
	SetsRepo repo;
	
	@Autowired
	SetsService serv;

	@Test
	void addSetTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Bench Press", "Chest", "Difficult", 20, "Rep");
		Workout wo = new Workout();
		Sets sets = new Sets(1, 8, 25, 3, er, wo);
		Mockito.when(repo.save(sets)).thenReturn(sets);
		
		sets = serv.addSet(sets);
		Assertions.assertEquals(8, sets.getRepetitions());
	}
	
	
	@Test
	void getSetTest() {
		int id = 1;
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Bench Press", "Chest", "Difficult", 20, "Rep");
		Workout wo = new Workout();
		Sets sets = new Sets();
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(new Sets(1, 8, 25, 3, er, wo)));
		
		sets = serv.getSet(id);
		Assertions.assertEquals(id, sets.getSet_id());
		Assertions.assertEquals(3, sets.getAmount_sets());	
	}
	
	@Test
	void getAllSetsTest() {
		List<Sets> allSets = new ArrayList<Sets>();
		Mockito.when(repo.findAll()).thenReturn(Stream.of(new Sets(), new Sets()).collect(Collectors.toList()));
		
		allSets = serv.getAllSets();
		Assertions.assertEquals(2, allSets.size());
	}
	
	@Test
	void updateSetTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Bench Press", "Chest", "Difficult", 20, "Rep");
		Workout wo = new Workout();
		Sets sets = new Sets(1, 8, 25, 3, er, wo);
		Mockito.when(repo.save(sets)).thenReturn(sets);
		
		Assertions.assertEquals(8, sets.getRepetitions());		
		sets.setRepetitions(12);
		sets = serv.updateSet(sets);
		Assertions.assertEquals(12, sets.getRepetitions());	
	}
	
	@Test
	void deleteSetTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Bench Press", "Chest", "Difficult", 20, "Rep");
		Workout wo = new Workout();
		Sets sets = new Sets(1, 8, 25, 3, er, wo);
		Mockito.when(repo.findById(sets.getSet_id())).thenReturn(Optional.of(sets));
		Mockito.when(repo.findById(0)).thenThrow(IllegalArgumentException.class);
		
		Assertions.assertEquals(true, serv.deleteSet(sets.getSet_id()));
		verify(repo, times(1)).delete(sets);
		
		Assertions.assertEquals(false, serv.deleteSet(0));
	}

}
