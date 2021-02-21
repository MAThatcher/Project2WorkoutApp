package com.revature.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GOALS_BODYWEIGHT")
public class GoalsBodyWeight {

	@Id
	@Column(updatable = false, name = "BODYWEIGHT_ID")
	@SequenceGenerator(name = "GOALS_BODYWEIGHT_SEQ", sequenceName = "GOALS_BODYWEIGHT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "GOALS_BODYWEIGHT_SEQ", strategy = GenerationType.SEQUENCE)
	private int bodyweight_id;
	
	@Column(name = "CURRENT_WEIGHT")
	private int current_weight;
	
	@Column(name = "GOAL_WEIGHT")
	private int goal_weight;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public GoalsBodyWeight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoalsBodyWeight(int bodyweight_id, int current_weight, int goal_weight, User user) {
		super();
		this.bodyweight_id = bodyweight_id;
		this.current_weight = current_weight;
		this.goal_weight = goal_weight;
		this.user = user;
	}

	public int getBodyweight_id() {
		return bodyweight_id;
	}

	public void setBodyweight_id(int bodyweight_id) {
		this.bodyweight_id = bodyweight_id;
	}

	public int getCurrent_weight() {
		return current_weight;
	}

	public void setCurrent_weight(int current_weight) {
		this.current_weight = current_weight;
	}

	public int getGoal_weight() {
		return goal_weight;
	}

	public void setGoal_weight(int goal_weight) {
		this.goal_weight = goal_weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "GoalsBodyweight [bodyweight_id=" + bodyweight_id + ", current_weight=" + current_weight
				+ ", goal_weight=" + goal_weight + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bodyweight_id;
		result = prime * result + current_weight;
		result = prime * result + goal_weight;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		GoalsBodyWeight other = (GoalsBodyWeight) obj;
		if (bodyweight_id != other.bodyweight_id)
			return false;
		if (current_weight != other.current_weight)
			return false;
		if (goal_weight != other.goal_weight)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
		
	
}
