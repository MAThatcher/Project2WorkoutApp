package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Actor;
import com.revature.repositories.ActorRepo;

@Service
public class ActorServiceImpl {

	@Autowired
	ActorRepo ar;
	
	
	public Actor addActor(Actor a) {
		return ar.save(a);
	}

	
	public Actor getActor(int id) {
		return ar.findById(id).get();
	}

	
	public List<Actor> getActor(String name) {
		return ar.findByName(name);
	}

	
	public List<Actor> getAllActors() {
		return (List<Actor>) ar.findAll();
	}

	
	public Actor updateActor(Actor change) {
		return ar.save(change); //saveOrUpdate
	}

	
	public boolean deleteActor(int id) {
		try {
			ar.delete(ar.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
