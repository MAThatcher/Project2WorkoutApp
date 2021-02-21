package com.revature.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Sets;
import com.revature.services.SetsService;
import com.revature.services.SetsServiceImpl;

@RestController
@CrossOrigin
public class SetsControllerImpl implements SetsController {

	@Autowired
	SetsService ss;
	//SetsServiceImpl ss;

	@GetMapping(value = "/sets/{id}")
	public Sets getSet(@PathVariable("id") int id) {
		try {
			return ss.getSet(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in SetsControllerImpl.getSet");
			// e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/sets", produces = "application/json")
	public List<Sets> getAllSets() {
		try {
			return ss.getAllSets();
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in SetsControllerImpl.getAllSets");
			// e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/sets", consumes = "application/json", produces = "application/json")
	public Sets addSet(@RequestBody Sets set) {
		try {
			return ss.addSet(set);
		} catch (Exception e) {
			System.out.println("Exception in SetsControllerImpl.addSet Likely duplicate value in unique column");
			// e.printStackTrace();
		}
		return null;
	}

	@PutMapping(value = "/sets/{id}", consumes = "application/json")
	public Sets updateSet(@PathVariable("id") int id, @RequestBody Sets change) {
		try {
			change.setSet_id(id);
			return ss.updateSet(change);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping(value = "/sets/{id}")
	public boolean deleteSet(@PathVariable("id") int id) {
		try {
			return ss.deleteSet(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in SetsControllerImpl.deleteSet");
			// e.printStackTrace();
		}
		return false;
	}
}
