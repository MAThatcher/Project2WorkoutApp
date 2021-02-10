package com.revature.controllers;

import java.util.List;


import com.revature.beans.Sets;

public interface SetsController {

	public Sets getSet(String id);
	public List<Sets> getAllSets();
	public Sets addSet(Sets set);
	public Sets updateSet(int id, Sets change);
	public boolean deleteSet(int id);
}
