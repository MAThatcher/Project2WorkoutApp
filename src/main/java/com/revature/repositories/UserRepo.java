package com.revature.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;


@Repository
public interface UserRepo extends CrudRepository <User, Integer>{
	
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);

}
