package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Sets;
import com.revature.repositories.SetsRepo;

public class SetsServiceImpl implements SetsService{

	@Autowired
	SetsRepo sr;
	
	@Override
	public Sets addSet(Sets set) {
		return sr.save(set);
	}
	
	public Sets getSet(int id) {
		return sr.findById(id).get();
	}

	public List<Sets> getAllSets() {
		return (List<Sets>) sr.findAll();
	}

	public Sets updateSet(Sets change) {
		return sr.save(change);
	}

	@Override
	public boolean deleteSet(int id) {
		try
		{
			sr.delete(sr.findById(id).get());
			return true;
		}
		catch(IllegalArgumentException e)
		{
			e.printStackTrace();
			return false;
		}
	}


}
