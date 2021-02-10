package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Workout;
import com.revature.services.WorkoutService;

@RestController
public class WorkoutController {
	
	@Autowired
	WorkoutService ws;
	
	@GetMapping(value = "/workout/{id}")
	public Workout getWorkout(@PathVariable("workout_id") String id) {
		return ws.getWorkout(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/workout", produces = "application/json")
	public List<Workout> getAllWorkouts() {
		System.out.println("Getting all workouts");
		return ws.getAllWorkouts();
	}
	
	@GetMapping(value="/workouts/search")
	public Workout getWorkoutByName(@RequestParam(required=true) String name) {
		return ws.getWorkout(name);
	}
	
	@PostMapping(value = "/workout", consumes="application/json", produces = "applicaion/json")
	public Workout addWorkout(@RequestBody Workout w) {
		return ws.addWorkout(w);
	}
	
	@PutMapping(value="/workout/{id}", consumes="application/json")
	public Workout updateWorkout(@PathVariable("workout_id") int id, @RequestBody Workout change) {
		change.setWorkout_id(id);
		return ws.updateWorkout(change);
	}
	
	@DeleteMapping(value="/workout/{id}")
	public boolean deleteWorkout(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return ws.deleteWorkout(id);
	}
	
	
	
	
	
	
	

}
