package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.beans.Sets;

public interface SetsController {

	public Sets getSet(@PathVariable("id") String id);
	public List<Sets> getAllSets();
	public Sets addSet(@RequestBody Sets set);
	public Sets updateSet(@PathVariable("id") int id, @RequestBody Sets change);
	public boolean deleteSet(@PathVariable("id") int id);
}
