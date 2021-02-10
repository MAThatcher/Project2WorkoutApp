package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.Workout;

public interface WorkoutRepo extends CrudRepository<Workout, Integer>{
	
	List<Workout> getWorkoutByName(String workout_name);
	List<Workout> getWorkoutById(int workout_id);
	
}
