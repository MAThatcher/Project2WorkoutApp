package com.revature.services;

import java.util.List;

import com.revature.beans.User;


public interface UserService {
	
	public User addUser(User user);
	//public User getUser(int id); //Alternate name
	public User findUserByID(int id);
	//public User getUser(String username); //Alternate name
	public User findUserByUsername(String username);
	public List<User> getAllUsers();
	public User updateUser(User change);
	public boolean deleteUser(int id);
	//public List<User>findByName(String name); //Alternate name
	
	//Non-CRUD
	public boolean registerUser(User user);	
	public User login(String username, String password);		
	public User logout(User user);

}
