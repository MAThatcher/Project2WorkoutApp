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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.ExerciseReference;
import com.revature.beans.GoalsBodyWeight;
import com.revature.beans.Sets;
import com.revature.beans.Workout;
import com.revature.services.SetsService;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class SetsControllerTests {
	
	@MockBean
	SetsService serv;
	
	@Autowired
	MockMvc mvc;

	@Test
	void getSetTest() throws Exception {
		int id = 1;
		Sets obj = new Sets();
		obj.setSet_id(1);
		obj.setRepetitions(10);
		obj.setTotal_calories(10);
		obj.setAmount_sets(3);
		ExerciseReference er = new ExerciseReference();
		obj.setExercise(er);
		Workout wo = new Workout();
		obj.setWorkout(wo);
		
		
		Mockito.when(serv.getSet(id)).thenReturn(obj); //Note: getWorkout(String name) not used in controller
		Mockito.when(serv.getSet(0)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/sets/1"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.repetitions", is(10)));
		
		ra = mvc.perform(get("/sets/0"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void getAllSetsTest() throws Exception {
		Mockito.when(serv.getAllSets()).thenReturn(Stream.of(new Sets(), new Sets(), new Sets()).collect(Collectors.toList()));		
		ResultActions ra = mvc.perform(get("/sets"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	void failGetAllSetsTest() throws Exception {
		Mockito.when(serv.getAllSets()).thenThrow(NoSuchElementException.class);		
		ResultActions ra = mvc.perform(get("/sets"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));		
	}
	
	@Test
	void addSetTest() throws Exception {
		Sets obj = new Sets();
		obj.setSet_id(1);
		obj.setRepetitions(10);
		obj.setTotal_calories(10);
		obj.setAmount_sets(3);
		ExerciseReference er = new ExerciseReference();
		obj.setExercise(er);
		Workout wo = new Workout();
		obj.setWorkout(wo);
		Sets testObj = new Sets();
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		
		Mockito.when(serv.addSet(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.addSet(ArgumentMatchers.eq(testObj))).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(post("/sets").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.repetitions", is(10)));
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(post("/sets").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void updateSetTest() throws Exception {
		Sets obj = new Sets();
		obj.setSet_id(1);
		obj.setRepetitions(10);
		obj.setTotal_calories(10);
		obj.setAmount_sets(3);
		ExerciseReference er = new ExerciseReference();
		obj.setExercise(er);
		Workout wo = new Workout();
		obj.setWorkout(wo);
		Sets testObj = new Sets();
		testObj.setSet_id(1);
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		Mockito.when(serv.updateSet(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.updateSet(ArgumentMatchers.eq(testObj))).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(put("/sets/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.repetitions", is(10)));	
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(put("/sets/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void deleteSetTest() throws Exception {
		int id = 1;
		int badID = 2;
		
		Mockito.when(serv.deleteSet(id)).thenReturn(true);
		Mockito.when(serv.deleteSet(badID)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(delete("/sets/" + id));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("true"));
		
		ra = mvc.perform(delete("/sets/" + badID));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("false"));
	}

}
