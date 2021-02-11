package com.revature.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.GoalsBodyWeight;
import com.revature.services.GoalsBodyWeightService;

@RestController
public class GoalsBodyWeightControllerImpl implements GoalsBodyWeightController {

	@Autowired
	GoalsBodyWeightService gs;

	@GetMapping(value = "/goalsbodyweight/{id}")
	public GoalsBodyWeight getGoal(@PathVariable("id") int id) {
		try {
			return gs.getGoal(id);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/goalsbodyweight", produces = "application/json")
	public List<GoalsBodyWeight> getAllGoals() {
		try {
			return gs.getAllGoals();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping(value = "/goalsbodyweight", consumes = "application/json", produces = "application/json")
	public GoalsBodyWeight addGoal(@RequestBody GoalsBodyWeight goal) {
		try {
			return gs.addGoal(goal);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PutMapping(value = "/goalsbodyweight/{id}", consumes = "application/json")
	public GoalsBodyWeight updateGoal(@PathVariable("id") int id, @RequestBody GoalsBodyWeight goal) {
		try {
			goal.setBodyweight_id(id);
			return gs.updateGoal(goal);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
	}

	@DeleteMapping(value = "/goalsbodyweight/{id}")
	public boolean deleteGoal(@PathVariable("id") int id) {
		try {
			return gs.deleteGoal(id);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

}
