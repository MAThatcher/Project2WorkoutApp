package com.revature.controllers;

import java.util.List;

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
		return ers.getExerciseReference(id);
	}

	@GetMapping(value = "/exerciseReference", produces = "application/json")
	public List<ExerciseReference> getAllExerciseReferences() {
		return ers.getAllExerciseReferences();
	}

	@PostMapping(value = "/exerciseReference", consumes = "application/json", produces = "application/json")
	public ExerciseReference addExerciseReference(@RequestBody ExerciseReference er) {
		return ers.addExerciseReference(er);
	}

	@PutMapping(value = "/exerciseReference/{id}", consumes = "application/json")
	public ExerciseReference updateExerciseReference(@PathVariable("id") int id, @RequestBody ExerciseReference er) {
		er.setId(id);
		return ers.updateExerciseReference(er);
	}

	@DeleteMapping(value = "/exerciseReference/{id}")
	public boolean deleteExerciseReference(@PathVariable("id") int id) {
		return ers.deleteExerciseReference(id);
	}

}
