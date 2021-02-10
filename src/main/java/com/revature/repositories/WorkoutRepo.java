package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.Workout;

public interface WorkoutRepo extends CrudRepository<Workout, Integer>{
	
	Workout getWorkoutByName(String workout_name);
	Workout getWorkoutById(int workout_id);
	
}
