package com.revature.services;

import java.util.List;

import com.revature.beans.ExerciseReference;

public interface ExerciseReferenceService {
	public ExerciseReference addExerciseReference(ExerciseReference er);
	public ExerciseReference getExerciseReference(int id);
	public List<ExerciseReference> getAllExerciseReferences();
	public ExerciseReference updateExerciseReference(ExerciseReference er);
	public boolean deleteExerciseReference(int id);

}
