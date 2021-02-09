package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Actor;

@Repository
public interface ActorRepo extends CrudRepository<Actor, Integer> {

	List<Actor> findByName(String name);
	List<Actor> findByNameAndWorth(String name, int worth);
	
}
