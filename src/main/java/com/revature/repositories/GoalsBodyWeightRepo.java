package com.revature.repositories;

import org.springframework.stereotype.Repository;

import com.revature.beans.GoalsBodyWeight;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface GoalsBodyWeightRepo extends CrudRepository<GoalsBodyWeight, Integer>{

}
