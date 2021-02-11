package com.revature.controllers;

import java.util.List;

import com.revature.beans.GoalsBodyWeight;

public interface GoalsBodyWeightController {

	public GoalsBodyWeight getGoal(int id);
	public List<GoalsBodyWeight> getAllGoals();
	public GoalsBodyWeight addGoal(GoalsBodyWeight goal);
	public GoalsBodyWeight updateGoal(int id, GoalsBodyWeight goal);
	public boolean deleteGoal(int id);
}
