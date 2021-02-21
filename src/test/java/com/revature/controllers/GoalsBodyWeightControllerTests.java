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
import com.revature.beans.User;
import com.revature.services.GoalsBodyWeightService;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class GoalsBodyWeightControllerTests {
	
	@MockBean
	GoalsBodyWeightService serv;
	
	@Autowired
	MockMvc mvc;

	@Test
	void getGoalTest() throws Exception {
		int id = 1;
		GoalsBodyWeight obj = new GoalsBodyWeight();
		obj.setBodyweight_id(1);
		obj.setCurrent_weight(200);
		obj.setGoal_weight(189);
		User user = new User();
		obj.setUser(user);
		
		Mockito.when(serv.getGoal(id)).thenReturn(obj); //Note: getWorkout(String name) not used in controller
		Mockito.when(serv.getGoal(0)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/goalsbodyweight/1"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.current_weight", is(200)));
		
		ra = mvc.perform(get("/goalsbodyweight/0"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void getAllGoalsTest() throws Exception {
		Mockito.when(serv.getAllGoals()).thenReturn(Stream.of(new GoalsBodyWeight(), new GoalsBodyWeight(), new GoalsBodyWeight()).collect(Collectors.toList()));		
		ResultActions ra = mvc.perform(get("/goalsbodyweight"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	void failGetAllGoalsTest() throws Exception {
		Mockito.when(serv.getAllGoals()).thenThrow(NoSuchElementException.class);		
		ResultActions ra = mvc.perform(get("/goalsbodyweight"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void addGoalTest() throws Exception {
		GoalsBodyWeight obj = new GoalsBodyWeight();
		obj.setBodyweight_id(1);
		obj.setCurrent_weight(200);
		obj.setGoal_weight(189);
		User user = new User();
		obj.setUser(user);
		GoalsBodyWeight testObj = new GoalsBodyWeight();
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		
		Mockito.when(serv.addGoal(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.addGoal(ArgumentMatchers.eq(testObj))).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(post("/goalsbodyweight").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.current_weight", is(200)));
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(post("/goalsbodyweight").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void updateGoalTest() throws Exception {
		GoalsBodyWeight obj = new GoalsBodyWeight();
		obj.setBodyweight_id(1);
		obj.setCurrent_weight(200);
		obj.setGoal_weight(189);
		User user = new User();
		obj.setUser(user);
		GoalsBodyWeight testObj = new GoalsBodyWeight();
		testObj.setBodyweight_id(1);
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		Mockito.when(serv.updateGoal(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.updateGoal(ArgumentMatchers.eq(testObj))).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(put("/goalsbodyweight/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.current_weight", is(200)));	
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(put("/goalsbodyweight/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void deleteGoalTest() throws Exception {
		int id = 1;
		int badID = 2;
		
		Mockito.when(serv.deleteGoal(id)).thenReturn(true);
		Mockito.when(serv.deleteGoal(badID)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(delete("/goalsbodyweight/" + id));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("true"));
		
		ra = mvc.perform(delete("/goalsbodyweight/" + badID));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("false"));
	}

}
