package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.GoalsBodyWeight;
import com.revature.repositories.GoalsBodyWeightRepo;

@Service
public class GoalsBodyWeightServiceImpl implements GoalsBodyWeightService{

	@Autowired
	GoalsBodyWeightRepo gr;
	
	@Override
	public GoalsBodyWeight addGoal(GoalsBodyWeight goal) {
		return gr.save(goal);
	}

	@Override
	public GoalsBodyWeight getGoal(int id) {
		return gr.findById(id).get();
	}

	@Override
	public List<GoalsBodyWeight> getAllGoals() {
		return(List<GoalsBodyWeight>) gr.findAll();
	}

	@Override
	public GoalsBodyWeight updateGoal(GoalsBodyWeight goal) {
		return gr.save(goal);
	}

	@Override
	public boolean deleteGoal(int id) {
		try
		{
			gr.delete(gr.findById(id).get());
			return true;
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	
}
