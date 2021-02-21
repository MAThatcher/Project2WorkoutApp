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
import com.revature.repositories.ExerciseReferenceRepo;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class ExerciseReferenceServiceTests {
	
	@MockBean
	ExerciseReferenceRepo err;
	
	@Autowired
	ExerciseReferenceService ers;

//	@Test
//	void test() {
//		//fail("Not yet implemented");
//		Assertions.assertTrue(false);
//	}
	
	@Test
	void addExerciseReferenceTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Deadlift", "Hamstrings", "Difficult", 20, "Rep");
		Mockito.when(err.save(er)).thenReturn(new ExerciseReference(er.getId(), er.getType(), er.getName(), er.getmGroup(), er.getDifficulty(), er.getCalories(), er.getUnit()));
		
		er = ers.addExerciseReference(er);
		Assertions.assertEquals("Deadlift", er.getName());
	}
	
	@Test
	void getExerciseReferenceTest() {
		int id = 1;
		ExerciseReference er = new ExerciseReference();
		Mockito.when(err.findById(id)).thenReturn(Optional.of(new ExerciseReference(id, "Weightlifting", "Deadlift", "Hamstrings", "Difficult", 20, "Rep")));
		
		er = ers.getExerciseReference(id);
		Assertions.assertEquals(id, er.getId());
		Assertions.assertEquals("Hamstrings", er.getmGroup());	
	}
	
	@Test
	void getAllExerciseReferencesTest() {
		List<ExerciseReference> allExRefs = new ArrayList<ExerciseReference>();
		Mockito.when(err.findAll()).thenReturn(Stream.of(new ExerciseReference(), new ExerciseReference()).collect(Collectors.toList()));
		
		allExRefs = ers.getAllExerciseReferences();
		Assertions.assertEquals(2, allExRefs.size());
	}
	
	@Test
	void updateExerciseReferenceTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Deadlift", "Hamstrings", "Difficult", 20, "Rep");
		Mockito.when(err.save(er)).thenReturn(er);
		
		Assertions.assertEquals("Deadlift", er.getName());		
		er.setName("Bench Press");
		er = ers.updateExerciseReference(er);
		Assertions.assertEquals("Bench Press", er.getName());	
	}
	
	@Test
	void deleteExerciseReferenceTest() {
		ExerciseReference er = new ExerciseReference(1, "Weightlifting", "Deadlift", "Hamstrings", "Difficult", 20, "Rep");
		Mockito.when(err.findById(er.getId())).thenReturn(Optional.of(er));
		Mockito.when(err.findById(0)).thenThrow(IllegalArgumentException.class); //Not sure how to use
		//Mockito.when(err.delete(er).thenReturn(true);
		
		Assertions.assertEquals(true, ers.deleteExerciseReference(er.getId()));
		verify(err, times(1)).delete(er);
		
		Assertions.assertEquals(false, ers.deleteExerciseReference(0));
	}
	
	@Test
	void getAllExerciseReferencesByTypeTest() {
		String type = "Weightlifting";
		List<ExerciseReference> allExRefs = new ArrayList<ExerciseReference>();
		
		Mockito.when(err.findAll()).thenReturn(Stream.of(new ExerciseReference(1, "Weightlifting", "Deadlift", "Hamstrings", "Difficult", 20, "Rep"), 
				new ExerciseReference(1, "Cardio", "Running", "Quadriceps", "Easy", 100, "Mile")).collect(Collectors.toList()));
		
		allExRefs = ers.getAllExerciseReferencesByType(type);
		Assertions.assertEquals(1, allExRefs.size());
		
		allExRefs = ers.getAllExerciseReferencesByType("NotAType");
		Assertions.assertEquals(0, allExRefs.size());
		
	}
	
	

}
