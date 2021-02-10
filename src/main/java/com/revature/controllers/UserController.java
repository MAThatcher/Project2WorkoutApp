package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	UserService us;
	
	@GetMapping(value = "/users/{id}")
	public User getUser(@PathVariable("id") String id) {
		return us.findUserByID(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() {
		System.out.println("Getting all Actors");
		return us.getAllUsers();
	}
	
	@GetMapping(value = "/users/search")
	public User getUserByUsername(@RequestParam(required = true) String username) {
		return us.findUserByUsername(username);
	}
	
	//To work with later for a login method
	@PostMapping(value = "/users/securelogin", consumes = "application/json", produces = "application/json")
	public User getUser(@RequestBody User user) {
		String username = user.getUsername();
		//String password = user.getPassword();
		
		//return us.login(username, password);
		return us.findUserByUsername(username);
	}
	
	//For adding/registering a new user; can change name to "registerUser" if desired
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
		return us.addUser(user);
	}
	
	@PutMapping(value = "/users/{id}", consumes = "application/json")
	public User updateActor(@PathVariable("id") int userID, @RequestBody User change) {
		change.setUserID(userID);
		return us.updateUser(change);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return us.deleteUser(id);
	}

}
