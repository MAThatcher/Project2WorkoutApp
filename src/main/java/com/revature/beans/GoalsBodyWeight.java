package com.revature.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	
	
}
