package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "goals_bodyweight")
public class goals_bodyweight {
	
	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "GOALS_BODYWEIGHT_SEQ", sequenceName = "GOALS_BODYWEIGHT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "GOALS_BODYWEIGHT_SEQ", strategy = GenerationType.SEQUENCE)
	int bodyweight_id;
	
	@Column(name = "current_weight")
	int current_weight;
	
	@Column(name = "goal_weight")
	int goal_weight;
	
	@Column(name = "user_id")
	int user_id;
	
	public goals_bodyweight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public goals_bodyweight(int bodyweight_id, int current_weight, int goal_weight, int user_id) {
		super();
		this.bodyweight_id = bodyweight_id;
		this.current_weight = current_weight;
		this.goal_weight = goal_weight;
		this.user_id = user_id;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "goals_bodyweight [bodyweight_id=" + bodyweight_id + ", current_weight=" + current_weight
				+ ", goal_weight=" + goal_weight + ", user_id=" + user_id + "]";
	}
	
	
}
