package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo ur;

	@Override
	public User addUser(User user) {
		return ur.save(user);
	}

	@Override
	public User findUserByID(int id) {
		return ur.findById(id).get();
	}

	@Override
	public User findUserByUsername(String username) { //Custom Method, may need to check if naming is correct
		return ur.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) ur.findAll();
	}

	@Override
	public User updateUser(User change) {
		return ur.save(change);
	}

	@Override
	public boolean deleteUser(int id) {
		try {
			ur.delete(ur.findById(id).get());
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Implement once database connection confirmed
		return false;
	}

	@Override
	public User login(String username, String password) {
		return ur.findByUsernameAndPassword(username, password);
	}

	@Override
	public User logout(User user) {
		// TODO Implement once database connection confirmed
		return null;
	}

}
