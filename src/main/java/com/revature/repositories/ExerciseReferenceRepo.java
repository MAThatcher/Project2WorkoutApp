package com.revature.repositories;

import java.util.List;

import com.revature.beans.ExerciseReference;

public interface ExerciseReferenceRepo{
	public int addExerciseReference(ExerciseReference er);
	public ExerciseReference getExerciseReference(int id);
	public List<ExerciseReference> getAllExerciseReferences();
	public ExerciseReference updateExerciseReference(ExerciseReference er);
	public boolean deleteExerciseReference(int id);
}
