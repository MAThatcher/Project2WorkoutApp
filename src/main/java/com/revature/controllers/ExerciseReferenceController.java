package com.revature.controllers;

import java.util.List;

import com.revature.beans.ExerciseReference;

public interface ExerciseReferenceController {
	public ExerciseReference getExerciseReference(int id);
	public List<ExerciseReference> getAllExerciseReferences();
	public ExerciseReference addExerciseReference(ExerciseReference er);
	public ExerciseReference updateExerciseReference(int id,ExerciseReference er);
	public boolean deleteExerciseReference( int id);
}
