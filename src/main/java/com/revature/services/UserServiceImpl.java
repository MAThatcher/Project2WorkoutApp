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
	public User registerUser(User user) {
		
		if(ur.findByUsername(user.getUsername()) == null) {
			return ur.save(user);
		} else {
			System.out.println("Username already taken");
			return null;
		}
		
		//return ur.save(user);
	}

	@Override
	public User login(String username, String password) {
		// TODO Implement once database connection confirmed
		User currentUser = this.findUserByUsername(username);
		
		try {
			if(currentUser != null) {
		
				//System.out.println("Input pass = " + password); //Testing
				//System.out.println("Received pass = " + currentUser.getPassword()); //Testing
				if(password.equals(currentUser.getPassword())) {
					
					System.out.println("Login Success!");
					return currentUser;
					
				} else {
					System.out.println("Incorrect Password");//Testing
					throw new Exception(); //OR IncorrectPasswordException or BadLoginException (maybe leave as not specific, like real websites)
				}
			} else {
				System.out.println("Username not found");//Testing
				throw new Exception(); //OR InvalidUsernameException OR overall BadLoginException
			}
			
		} catch(Exception e) {
			System.out.println("Invalid Username or Password"); //Temporary->Throw exception to calling code, throw exception here, or message-out here
		}
		
		return null;
	}

	@Override
	public User logout(User user) {
		System.out.println("Logging out...");
		user = null;
		
		return user;		
	}

}
