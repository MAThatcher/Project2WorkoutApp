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

import com.revature.beans.ExerciseReference;
import com.revature.services.ExerciseReferenceService;

@RestController
public class ExerciseReferenceControllerImpl implements ExerciseReferenceController {
	@Autowired
	ExerciseReferenceService ers;

	@GetMapping(value = "/exerciseReference/{id}")
	public ExerciseReference getExerciseReference(@PathVariable("id") int id) {
		try {
			return ers.getExerciseReference(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in ExerciseReferencesController.getExerciseReference");
			// e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/exerciseReference", produces = "application/json")
	public List<ExerciseReference> getAllExerciseReferences() {
		try {
			return ers.getAllExerciseReferences();
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in ExerciseReferencesController.getAllExerciseReferences");
			// e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/exerciseReference", consumes = "application/json", produces = "application/json")
	public ExerciseReference addExerciseReference(@RequestBody ExerciseReference er) {
		try {
			return ers.addExerciseReference(er);
		} catch (Exception e) {
			System.out.println("Exception in ExerciseReferencesController.addExerciseReference Likely duplicate value in unique column");
			// e.printStackTrace();
		}
		return null;
	}
	

	@PutMapping(value = "/exerciseReference/{id}", consumes = "application/json")
	public ExerciseReference updateExerciseReference(@PathVariable("id") int id, @RequestBody ExerciseReference er) {
		try {
			er.setId(id);
			return ers.updateExerciseReference(er);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping(value = "/exerciseReference/{id}")
	public boolean deleteExerciseReference(@PathVariable("id") int id) {
		try {
			return ers.deleteExerciseReference(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in ExerciseReferencesController.deleteExerciseReference");
			// e.printStackTrace();
		}
		return false;
	}

}
