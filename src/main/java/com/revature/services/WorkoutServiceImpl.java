package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.beans.Workout;
import com.revature.repositories.WorkoutRepo;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	WorkoutRepo wr;

	@Override
	public Workout addWorkout(Workout w) {
		return wr.save(w);
	}

	@Override
	public Workout getWorkout(int id) {
		return wr.findById(id).get();
	}

	@Override
	public Workout getWorkout(String name) {
		return wr.findByName(name);
	}

	@Override
	public List<Workout> getAllWorkouts() {
		return (List<Workout>) wr.findAll();
	}

	@Override
	public Workout updateWorkout(Workout change) {
		return wr.save(change);
	}

	@Override
	public boolean deleteWorkout(int id) {
		try {
			wr.delete(wr.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Workout> getAllWorkoutsByUserId(int id)
	{
		//All the work outs
		ArrayList<Workout> workouts = (ArrayList<Workout>) wr.findAll();
		//Arraylist to hold the user work outs
		ArrayList<Workout> userWorkOuts = new ArrayList<Workout>();
		//Search through all work outs if user id matches id in params add work out to user workout
		for(Workout workout : workouts)
		{
			if(workout.getUser().getUserID() == id)
			{
				userWorkOuts.add(workout);
			}
		}
		
		return userWorkOuts;
	}

}
