package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Sets;
import com.revature.services.SetsServiceImpl;

@RestController
public class SetsControllerImpl implements SetsController{

	@Autowired
	SetsServiceImpl ss;
	
	@GetMapping(value = "/sets/{id}")
	public Sets getSet(@PathVariable("id") String id)
	{
		return ss.getSet(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/sets", produces = "application/json")
	public List<Sets> getAllSets()
	{
		return ss.getAllSets();
	}
	
	@PostMapping(value = "/sets", consumes = "application/json", produces = "application/json")
	public Sets addSet(@RequestBody Sets set)
	{
		return ss.addSet(set);
	}
	
	@PutMapping(value = "/sets/{id}", consumes = "application/json")
	public Sets updateSet(@PathVariable("id") int id, @RequestBody Sets change)
	{
		change.setSet_id(id);
		return ss.updateSet(change);
	}
	
	@DeleteMapping(value = "/sets/{id}")
	public boolean deleteSet(@PathVariable("id") int id)
	{
		return ss.deleteSet(id);
	}
}
