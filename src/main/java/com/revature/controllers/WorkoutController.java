package com.revature.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.beans.Workout;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.services.WorkoutService;
import com.revature.services.WorkoutServiceImpl;

@RestController
@CrossOrigin
public class WorkoutController {
	
	@Autowired
	WorkoutService ws;
	//WorkoutServiceImpl ws;
	@Autowired
	UserService us;
	//UserServiceImpl us;
	
	@GetMapping(value = "/workout/{id}")
	public Workout getWorkout(@PathVariable("id") int id) {
		try
		{
			return ws.getWorkout(id);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("NoSuchElementException in WorkoutController.getWorkout");
			// e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/workout", produces = "application/json")
	public List<Workout> getAllWorkouts() {
		try
		{
			return ws.getAllWorkouts();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("NoSuchElementException in WorkoutController.getAllWorkouts");
			// e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value = "/workout", consumes="application/json", produces = "application/json")
	public Workout addWorkout(@RequestBody Workout w, HttpServletRequest request) {
		try
		{
			Cookie[] cookies = request.getCookies();
			String cookieId = cookies[0].getValue();
			int id = Integer.parseInt(cookieId);
			System.out.println(id);
			User loggedInUser = us.findUserByID(id);
			w.setUser(loggedInUser);
			return ws.addWorkout(w);
		}
		catch(Exception e)
		{
			System.out.println("Exception in WorkoutController.addWorkout Likely duplicate value in unique column");
			// e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping(value="/workout/{id}", consumes="application/json")
	public Workout updateWorkout(@PathVariable("id") int id, @RequestBody Workout change) {
		try
		{
			change.setWorkout_id(id);
			return ws.updateWorkout(change);
		}
		catch(Exception e)
		{
			System.out.println("Exception in WorkoutController.updateWorkout Likely duplicate value in unique column");
			// e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping(value="/workout/{id}")
	public boolean deleteWorkout(@PathVariable("id") int id) {
		try
		{
			return ws.deleteWorkout(id);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("NoSuchElementException in WorkoutController.deleteWorkout");
			// e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping(value = "/getUserWorkOut/{id}")
	public List<Workout> getUserWorkOut(@PathVariable("id") int id) {
		try {
			return ws.getAllWorkoutsByUserId(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in ExerciseReferencesController.getExerciseReference");
			// e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	

}
