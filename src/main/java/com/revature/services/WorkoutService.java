package com.revature.services;

import java.util.List;

import com.revature.beans.Workout;

public interface WorkoutService {

	public Workout addWorkout(Workout w);
	public Workout getWorkout(int id);
	public Workout getWorkout(String name);
	public List<Workout> getAllWorkouts();
	public Workout updateWorkout(Workout change);
	public boolean deleteWorkout(int id);
	
}
