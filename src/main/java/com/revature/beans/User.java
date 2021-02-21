package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
//@Scope("session")
public class User {
	
	//Fields
	
	@Id //PK
	//@Column(name = "user_id") //Names must match db
	@Column(updatable = false, name = "user_id") //Cannot change PK
	@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1) 
	@GeneratedValue(generator = "USERS_SEQ", strategy = GenerationType.SEQUENCE)
	private int userID; //PK NUM
	
	@Column(name = "user_fname")
	private String fname;//VARCHAR2
	
	@Column(name = "user_lname")
	private String lname;//CHAR
	
	@Column(name = "user_username", unique = true)
	private String username; //CHAR
	
	@Column(name = "user_password")
	private String password; //CHAR
	
	@Column(name = "user_email", unique = true)
	private String email;	//CHAR
	
	@Column(name = "user_phone_number")
	private String phNumber; //VARCHAR2
	
	@Column(name = "user_gender")
	private String gender; //VARCHAR2
	
	@Column(name = "user_age")
	private int age; //NUM
	
	@Column(name = "user_weight")
	private int weight; //NUM
	
	@Column(name = "user_goal_weight")
	private int goalWeight; //NUM
	
	@Column(name = "user_height")
	private int height; //NUM
	
	@Column(name = "user_time")
	private String time; //VARCHAR2

	
	
	//Constructors
	
	public User() {
		super();
	}



	public User(int userID, String fname, String lname, String username, String password, String email, String phNumber,
			String gender, int age, int weight, int goalWeight, int height, String time) {
		super();
		this.userID = userID;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phNumber = phNumber;
		this.gender = gender;
		this.age = age;
		this.weight = weight;
		this.goalWeight = goalWeight;
		this.height = height;
		this.time = time;
	}



	public User(String fname, String lname, String username, String password, String email, String phNumber,
			String gender, int age, int weight, int goalWeight, int height, String time) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phNumber = phNumber;
		this.gender = gender;
		this.age = age;
		this.weight = weight;
		this.goalWeight = goalWeight;
		this.height = height;
		this.time = time;
	}

	//Getters and Setters

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getGoalWeight() {
		return goalWeight;
	}

	public void setGoalWeight(int goalWeight) {
		this.goalWeight = goalWeight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	//To String

	@Override
	public String toString() {
		return "User [userID=" + userID + ", fname=" + fname + ", lname=" + lname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", phNumber=" + phNumber + ", gender=" + gender
				+ ", age=" + age + ", weight=" + weight + ", goalWeight=" + goalWeight + ", height=" + height
				+ ", time=" + time + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + goalWeight;
		result = prime * result + height;
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phNumber == null) ? 0 : phNumber.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + weight;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (goalWeight != other.goalWeight)
			return false;
		if (height != other.height)
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phNumber == null) {
			if (other.phNumber != null)
				return false;
		} else if (!phNumber.equals(other.phNumber))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	
	
}
