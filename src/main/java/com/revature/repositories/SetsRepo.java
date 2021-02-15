package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Sets;

@Repository
public interface SetsRepo extends CrudRepository<Sets, Integer>{

}
