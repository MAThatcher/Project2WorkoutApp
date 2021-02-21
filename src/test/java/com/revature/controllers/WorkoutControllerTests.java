package com.revature.controllers;

import static org.hamcrest.CoreMatchers.is;
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
import com.revature.beans.User;
import com.revature.beans.Workout;
import com.revature.services.UserService;
import com.revature.services.WorkoutService;

@AutoConfigureMockMvc
@SpringBootTest(classes = com.example.demo.Project2WorkoutAppApplication.class)
class WorkoutControllerTests {
	
	@MockBean
	WorkoutService serv;
	
	@MockBean
	UserService userv;
	
	@Autowired
	MockMvc mvc;

	@Test
	void getWorkoutTest() throws Exception {
		int id = 1;
		Workout obj = new Workout();
		obj.setWorkout_id(1);
		obj.setName("Squat");
		
		Mockito.when(serv.getWorkout(id)).thenReturn(obj); //Note: getWorkout(String name) not used in controller
		Mockito.when(serv.getWorkout(0)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/workout/1"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));
		
		ra = mvc.perform(get("/workout/0"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));	
	}
	
	@Test
	void getAllWorkoutsTest() throws Exception {
		Mockito.when(serv.getAllWorkouts()).thenReturn(Stream.of(new Workout(), new Workout(), new Workout()).collect(Collectors.toList()));		
		ResultActions ra = mvc.perform(get("/workout"));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
	}
	
	@Test
	void failGetAllWorkoutsTest() throws Exception {
		Mockito.when(serv.getAllWorkouts()).thenThrow(NoSuchElementException.class); //leaving null just throws "Exception"
		ResultActions ra = mvc.perform(get("/workout"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void getUserWorkOutTest() throws Exception {
		int id = 1;
		Mockito.when(serv.getAllWorkoutsByUserId(id)).thenReturn(Stream.of(new Workout(), new Workout(), new Workout()).collect(Collectors.toList()));
		Mockito.when(serv.getAllWorkoutsByUserId(0)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(get("/getUserWorkOut/" + id));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.length()", is(3)));
		
		ra = mvc.perform(get("/getUserWorkOut/0"));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));	
	}
	
	@Test
	void addWorkoutTest() throws Exception {
		int id = 1;
		Workout obj = new Workout();
		obj.setWorkout_id(1);
		obj.setName("Squat");
		obj.setDuration(30);
		obj.setmGroup("Quadriceps");
		obj.setCalories(300);
		User user = new User();
		obj.setUser(user);
		Workout testObj = new Workout();
		testObj.setUser(user);
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		
		Mockito.when(userv.findUserByID(id)).thenReturn(user);
		Mockito.when(serv.addWorkout(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.addWorkout(ArgumentMatchers.eq(testObj))).thenThrow(NullPointerException.class);
		
		ResultActions ra = mvc.perform(post("/workout").contentType(MediaType.APPLICATION_JSON).content(jsonRequest).cookie(new Cookie("id", String.valueOf(id))));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(post("/workout").contentType(MediaType.APPLICATION_JSON).content(jsonRequest).cookie(new Cookie("id", String.valueOf(id))));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void updateWorkoutTest() throws Exception {
		Workout obj = new Workout();
		obj.setWorkout_id(1);
		obj.setName("Squat");
		obj.setDuration(30);
		obj.setmGroup("Quadriceps");
		obj.setCalories(300);
		User user = new User();
		obj.setUser(user);
		Workout testObj = new Workout();
		testObj.setWorkout_id(1);
		
		ObjectMapper om = new ObjectMapper();
		String jsonRequest = om.writeValueAsString(obj);
		Mockito.when(serv.updateWorkout(ArgumentMatchers.eq(obj))).thenReturn(obj);
		Mockito.when(serv.updateWorkout(ArgumentMatchers.eq(testObj))).thenThrow(NullPointerException.class);
		
		ResultActions ra = mvc.perform(put("/workout/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("$.name", is("Squat")));	
		
		jsonRequest = om.writeValueAsString(testObj);
		ra = mvc.perform(put("/workout/1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string(""));
	}
	
	@Test
	void deleteWorkoutTest() throws Exception {
		int id = 1;
		int badID = 2;
		
		Mockito.when(serv.deleteWorkout(id)).thenReturn(true);
		Mockito.when(serv.deleteWorkout(badID)).thenThrow(NoSuchElementException.class);
		
		ResultActions ra = mvc.perform(delete("/workout/" + id));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("true"));
		
		ra = mvc.perform(delete("/workout/" + badID));
		ra.andExpect(status().isOk());
		ra.andExpect(content().string("false"));
	}

}
