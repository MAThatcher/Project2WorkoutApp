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

import com.revature.beans.Actor;
import com.revature.services.ActorServiceImpl;

@RestController
public class ActorController {
	
	@Autowired
	ActorServiceImpl as;
	
	@GetMapping(value = "/actors/{id}")
	public Actor getActor(@PathVariable("id") String id) {
		return as.getActor(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/actors", produces = "application/json")
	public List<Actor> getAllActors() {
		System.out.println("Getting all Actors");
		return as.getAllActors();
	}
	
	@GetMapping(value = "/actors/search")
	public List<Actor> getActorByName(@RequestParam(required = true) String name) {
		return as.getActor(name);
	}
	
	@PostMapping(value = "/actors", consumes = "application/json", produces = "application/json")
	public Actor addActor(@RequestBody Actor a) {
		return as.addActor(a);
	}
	
	@PutMapping(value = "/actors/{id}", consumes = "application/json")
	public Actor updateActor(@PathVariable("id") int id, @RequestBody Actor change) {
		change.setId(id);
		return as.updateActor(change);
	}
	
	@DeleteMapping(value = "/actors/{id}")
	public boolean deleteActor(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return as.deleteActor(id);
	}

}
