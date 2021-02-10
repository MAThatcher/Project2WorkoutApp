package com.revature.services;

import java.util.List;

import com.revature.beans.Sets;

public interface SetsService {
	public Sets addSet(Sets set);
	public Sets getSet(int id);
	public List<Sets> getAllSets();
	public Sets updateSet(Sets set);
	public boolean deleteSet(int id);
}
