package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author reu60
 *
 */
@Entity
@Table(name="WORKOUT")
public class Workout {

	@Id
	@Column(updatable=false)
	@SequenceGenerator(name="WORKOUT_SEQ", sequenceName="WORKOUT_SEQ", allocationSize=1)
	@GeneratedValue(generator="WORKOUT_SEQ", strategy=GenerationType.SEQUENCE)
	private int workout_id;
	
	@Column(name = "WORKOUT_NAME", unique = true)
	private String workout_name;
	
	@Column(name = "WORKOUT_DURATION")
	private int workout_duration;
	
	@Column(name = "WORKOUT_MUSCLE_GROUP")
	private String workout_muscle_group;
	
	@Column(name = "WORKOUT_CALORIES")
	private int workout_calories;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	//no-args constructor
	public Workout() {
		super();
	}
	
	public Workout() {
		super();
	}

	public Workout(int workout_id, String workout_name, int workout_duration, String workout_muscle_group,
			int workout_calories, User user) {
		super();
		this.workout_id = workout_id;
		this.workout_name = workout_name;
		this.workout_duration = workout_duration;
		this.workout_muscle_group = workout_muscle_group;
		this.workout_calories = workout_calories;
		this.user = user;
	}

	public Workout(String workout_name, int workout_duration, String workout_muscle_group, int workout_calories,
			User user) {
		super();
		this.workout_name = workout_name;
		this.workout_duration = workout_duration;
		this.workout_muscle_group = workout_muscle_group;
		this.workout_calories = workout_calories;
		this.user = user;
	}

	public int getWorkout_id() {
		return workout_id;
	}

	public void setWorkout_id(int workout_id) {
		this.workout_id = workout_id;
	}

	public String getWorkout_name() {
		return workout_name;
	}

	public void setWorkout_name(String workout_name) {
		this.workout_name = workout_name;
	}

	public int getWorkout_duration() {
		return workout_duration;
	}

	public void setWorkout_duration(int workout_duration) {
		this.workout_duration = workout_duration;
	}

	public String getWorkout_muscle_group() {
		return workout_muscle_group;
	}

	public void setWorkout_muscle_group(String workout_muscle_group) {
		this.workout_muscle_group = workout_muscle_group;
	}

	public int getWorkout_calories() {
		return workout_calories;
	}

	public void setWorkout_calories(int workout_calories) {
		this.workout_calories = workout_calories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Workout [workout_id=" + workout_id + ", workout_name=" + workout_name + ", workout_duration="
				+ workout_duration + ", workout_muscle_group=" + workout_muscle_group + ", workout_calories="
				+ workout_calories + "]";
	}
	
	
	
	

	
	
	
	
	

}
