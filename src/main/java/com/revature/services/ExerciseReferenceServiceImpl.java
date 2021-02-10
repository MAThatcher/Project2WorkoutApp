package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.ExerciseReference;
import com.revature.repositories.ExerciseReferenceRepo;

@Service
public class ExerciseReferenceServiceImpl implements ExerciseReferenceService{

	@Autowired
	ExerciseReferenceRepo err;

	@Override
	public ExerciseReference addExerciseReference(ExerciseReference er) {
		er.setId(err.addExerciseReference(er));
		return er;
	}

	@Override
	public ExerciseReference getExerciseReference(int id) {
		return err.getExerciseReference(id);
	}

	@Override
	public List<ExerciseReference> getAllExerciseReferences() {
		return err.getAllExerciseReferences();
	}

	@Override
	public ExerciseReference updateExerciseReference(ExerciseReference er) {
		return err.updateExerciseReference(er);
	}

	@Override
	public boolean deleteExerciseReference(int id) {
		return err.deleteExerciseReference(id);
	}
	
}
