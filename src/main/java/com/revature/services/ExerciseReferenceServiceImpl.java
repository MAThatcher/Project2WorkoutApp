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
		return err.save(er);
	}

	@Override
	public ExerciseReference getExerciseReference(int id) {
		return err.findById(id).get();
	}

	@Override
	public List<ExerciseReference> getAllExerciseReferences() {
		return (List<ExerciseReference>) err.findAll();
	}

	@Override
	public ExerciseReference updateExerciseReference(ExerciseReference er) {
		return err.save(er);
	}

	@Override
	public boolean deleteExerciseReference(int id) {
		try {
			err.delete(err.findById(id).get());
			return true;
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
