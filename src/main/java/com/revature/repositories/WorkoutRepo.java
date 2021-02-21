package com.revature.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;
import com.revature.beans.Workout;

@Repository
public interface WorkoutRepo extends CrudRepository<Workout, Integer> {
	ArrayList<Workout> findAllByUser(User user);
	Workout findByName(String name);

}
