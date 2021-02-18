package com.revature.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

//import com.google.gson.Gson;
import com.revature.beans.User;
import com.revature.services.UserService;

@RestController
//@Scope("session")
@SessionAttributes("loggedInUser")
public class UserController {

	@Autowired
	UserService us;
	
	//public static Gson gson = new Gson();
	
//	@Autowired
//	HttpSession sess;
	
//	@Autowired
//	private User loggedInUser;

	@GetMapping(value = "/users/{id}")
	@CrossOrigin
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
	@PostMapping(value = "/users/securelogin", consumes = "application/json", produces = "application/json")
	@CrossOrigin
	//@ModelAttribute("loggedInUser")
	//public @ResponseBody @ModelAttribute("loggedInUser") User getUser(@SessionAttribute("loggedInUser") @RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
	public @ResponseBody User getUser(@RequestBody User user, /*HttpSession sess*/HttpServletRequest request, Model model) {
		
		//model.addAttribute
		//HttpSession sess = request.getSession();
		//Model model = new Model();
		try {			
			String username = user.getUsername();
			String password = user.getPassword();
			//user = us.findUserByUsername(username); //Testing
			user = us.login(username, password);
			HttpSession sess = request.getSession();
			
			//System.out.println("User: " + user);
//			sess.setAttribute("loggedInUser", user); //This is the problem
//			System.out.println("Session User: " + sess.getAttribute("loggedInUser"));
			System.out.println("Session ID (login): " + sess.getId());
			model.addAttribute("loggedInUser", user);
			System.out.println("User after attempting add: " + model.getAttribute("loggedInUser"));
			
			//response.getWriter().append(gson.toJson(user));
			
			//return us.login(username, password);
			return user;
		} catch (Exception e) {
			//sess.invalidate();
			System.out.println("Exception in UserController.getUser login method");
			e.printStackTrace();
		}
		return null;

	}
	
	@PostMapping(value = "/users/checkuser", produces = "application/json")
	@CrossOrigin
	//public @ResponseBody User checkUser(/*HttpServletRequest request @SessionAttribute("loggedInUser") User user*/) {
	//public @ResponseBody User checkUser(HttpSession session /*ServletRequest request, HttpServletResponse response*/) {
	public @ResponseBody User checkUser(/*@ModelAttribute("loggedInUser") User user, HttpSession sess,*/HttpServletRequest request, Model model) {
		//sess = session;
		//HttpSession sess = request.getSession();
		HttpSession sess = request.getSession();
		System.out.println("Session ID (checkUser): " + sess.getId());		
		//User user = (User) session.getAttribute("loggedInUser");
		//System.out.println(user);
		//User user = loggedInUser;
		User user = new User();
		System.out.println(model.containsAttribute("loggedInUser"));
		user = (User) model.getAttribute("loggedInUser");
		System.out.println("Current Logged-in User: " + user);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}
	
	@ModelAttribute("loggedInUser")
    public User testGetUser () {
		User user = new User();
		user.setFname("Smith");
		
        return user;
    }
	

	// Logout
	@GetMapping(value = "/users/logout")
	public User logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = cookies[0];
		System.out.println("Current Cookie Value: " + userCookie.getValue());
		userCookie.setValue(null);
		System.out.println("(Should be) Null Cookie Value: " + userCookie.getValue());
		userCookie.setMaxAge(0);
		
		response.addCookie(userCookie);

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
	public User updateUser(@PathVariable("id") int userID, @RequestBody User change) {
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
