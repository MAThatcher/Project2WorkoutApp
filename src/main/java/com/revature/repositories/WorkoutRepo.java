package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Workout;

@Repository
public interface WorkoutRepo extends CrudRepository<Workout, Integer> {

	Workout findWorkoutByName(String workout_name);

}
