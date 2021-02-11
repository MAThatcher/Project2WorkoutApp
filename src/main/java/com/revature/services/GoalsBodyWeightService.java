package com.revature.services;

import java.util.List;

import com.revature.beans.GoalsBodyWeight;

public interface GoalsBodyWeightService {
	public GoalsBodyWeight addGoal(GoalsBodyWeight goal);
	public GoalsBodyWeight getGoal(int id);
	public List<GoalsBodyWeight> getAllGoals();
	public GoalsBodyWeight updateGoal(GoalsBodyWeight goal);
	public boolean deleteGoal(int id);
}
