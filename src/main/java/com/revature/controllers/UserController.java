package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	UserService us;
	
	@Autowired
	HttpSession sess;
	
	@GetMapping(value = "/users/{id}")
	public User getUser(@PathVariable("id") String id) {
		return us.findUserByID(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() {
		System.out.println("Getting all Users");
		return us.getAllUsers();
	}
	
	@GetMapping(value = "/users/search")
	public User getUserByUsername(@RequestParam(required = true) String username) {
		return us.findUserByUsername(username);
	}
	
	//Login method
	@PostMapping(value = "/users/securelogin", consumes = "application/json"/*, produces = "application/json"*/)
	public User getUser(@RequestBody User user) {
		
		//System.out.println("Here, at SecureLogin method");
		String username = user.getUsername();
		String password = user.getPassword();
		//System.out.println("Username: " + username);
		user = us.findUserByUsername(username);
		//System.out.println("Full user if found:\n" + user);
		
		//HttpSession sess = request.;
		sess.setAttribute("loggedInUser", user);
		System.out.println("Session ID (login): " + sess.getId());
			
		//return us.login(username, password);
		//return us.findUserByUsername(username);
		//return us.findUserByID(user.getUserID());
		return user;
	}
	
	//Register
	//For adding/registering a new user; can change name to "registerUser" if desired
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
		System.out.println("Registering User (controller)...");
		//return us.registerUser(user)
		return us.addUser(user);
	}
	
	//Logout
	@PostMapping(value = "/users/logout")
	public User logout() {
		sess.invalidate();
		return null;
	}
	
	@PutMapping(value = "/users/{id}", consumes = "application/json")
	public User updateUser(@PathVariable("id") int userID, @RequestBody User change) {
		change.setUserID(userID);
		return us.updateUser(change);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		System.out.println("Executing Delete");
		return us.deleteUser(id);
	}

}
