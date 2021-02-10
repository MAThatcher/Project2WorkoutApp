package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.ExerciseReference;

@Repository
public interface ExerciseReferenceRepo extends CrudRepository<ExerciseReference, Integer> {
}
