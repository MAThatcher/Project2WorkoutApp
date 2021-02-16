package com.revature.controllers;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
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

@RestController
public class UserController {

	@Autowired
	UserService us;
	@Autowired
	HttpSession sess;
	
	@GetMapping(value = "/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		try {
			return us.findUserByID(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in UserController.getUser");
			// e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() {
		try {
			return us.getAllUsers();
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in UserController.getAllUsers");
			// e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/users/search")
	public User getUserByUsername(@RequestParam(required = true) String username) {
		try {
			return us.findUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// To work with later for a login method
	@GetMapping(value = "/users/securelogin")
	public User getUserLogin(@RequestParam(required = true) String username, String password, HttpServletResponse response) {
		User loggedInUser = us.login(username, password);
		Cookie cookie = new Cookie("id", String.valueOf(loggedInUser.getUserID()));
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setSecure(true);
		response.addCookie(cookie);
		
		return loggedInUser;
	}

	// To work with later for a login method
	@GetMapping(value = "/users/viewLoggedInUser")
	public User viewUserLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String cookieId = cookies[0].getValue();
		int id = Integer.parseInt(cookieId);
		System.out.println(id);
		User loggedInUser = us.findUserByID(id);
		System.out.println(loggedInUser.toString());
		return loggedInUser;
	}
	
	// Logout
	@PostMapping(value = "/users/logout")
	public User logout() {

		return null;
	}

	// For adding/registering a new user; can change name to "registerUser" if
	// desired
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User user) {
		try {
			return us.addUser(user);
		} catch (Exception e) {
			System.out.println("Exception in UserController.addUser Likely duplicate value in unique column");
			// e.printStackTrace();
		}
		return null;

	}

	@PutMapping(value = "/users/{id}", consumes = "application/json")
	public User updateActor(@PathVariable("id") int userID, @RequestBody User change) {
		try {
			change.setUserID(userID);
			return us.updateUser(change);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping(value = "/users/{id}")
	public boolean deleteUser(@PathVariable("id") int id) {
		try {
			return us.deleteUser(id);
		} catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException in UserController.deleteUser");
			// e.printStackTrace();
		}
		return false;
	}

}
