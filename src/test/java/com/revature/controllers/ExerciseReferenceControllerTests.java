package com.revature.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.ExerciseReference;
import com.revature.beans.Workout;
import com.revature.services.ExerciseReferenceService;
import com.revature.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class ExerciseReferenceControllerTests {
	
	@MockBean
	ExerciseReferenceService serv;
	
	@Autowired
	MockMvc mvc;

	@Test
	void getExerciseReferenceTest() throws Exception {
		int id = 1;
		ExerciseReference obj = new ExerciseReference();
		obj.setId(1);
		obj.setName("Squat");
		
		Mockito.when(serv.getExerciseReference(id)).thenReturn(obj); //Note: getWorkout(String name) not used in controller
		Mockito.when(serv.getExerciseReference(0)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/exerciseReference/1"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));
		
		ra = mvc.perform(get("/exerciseReference/0"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void getAllExerciseReferencesTest() throws Exception {
		Mockito.when(serv.getAllExerciseReferences()).thenReturn(Stream.of(new ExerciseReference(), new ExerciseReference(), new ExerciseReference()).collect(Collectors.toList()));		
		ResultActions ra = mvc.perform(get("/exerciseReference"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	void failGetAllExerciseReferencesTest() throws Exception {
		Mockito.when(serv.getAllExerciseReferences()).thenThrow(NoSuchElementException.class);		
		ResultActions ra = mvc.perform(get("/exerciseReference"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void getAllExerciseReferencesByTypeTest() throws Exception {
		String type = "LowerBody";
		String badType = "UpperBody";
		Mockito.when(serv.getAllExerciseReferencesByType(type)).thenReturn(Stream.of(new ExerciseReference(), new ExerciseReference(), new ExerciseReference()).collect(Collectors.toList()));
		Mockito.when(serv.getAllExerciseReferencesByType(badType)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/exerciseReferenceByType/LowerBody"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
		
		ra = mvc.perform(get("/exerciseReferenceByType/UpperBody"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void addExerciseReferenceTest() throws Exception {
		int id = 1;
		ExerciseReference obj = new ExerciseReference();
		obj.setId(1);
		obj.setName("Squat");
		ExerciseReference testObj = new ExerciseReference();
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		
		Mockito.when(serv.addExerciseReference(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.addExerciseReference(ArgumentMatchers.eq(testObj))).thenThrow(NullPointerException.class);
		
		ResultActions ra = mvc.perform(post("/exerciseReference").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(post("/exerciseReference").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void updateExerciseReferenceTest() throws Exception {
		int id = 1;
		ExerciseReference obj = new ExerciseReference();
		obj.setId(1);
		obj.setName("Squat");
		ExerciseReference testObj = new ExerciseReference();
		testObj.setId(1);
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		Mockito.when(serv.updateExerciseReference(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.updateExerciseReference(ArgumentMatchers.eq(testObj))).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(put("/exerciseReference/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));	
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(put("/exerciseReference/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void deleteExerciseReferenceTest() throws Exception {
		int id = 1;
		int badID = 2;
		
		Mockito.when(serv.deleteExerciseReference(id)).thenReturn(true);
		Mockito.when(serv.deleteExerciseReference(badID)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(delete("/exerciseReference/" + id));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("true"));
		
		ra = mvc.perform(delete("/exerciseReference/" + badID));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("false"));
	}

}
