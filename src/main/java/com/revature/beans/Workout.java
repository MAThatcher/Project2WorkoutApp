package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="WORKOUT")
public class Workout {
	
	//Test Comment --append

	@Id
	@Column(updatable=false)
	@SequenceGenerator(name="WORKOUT_SEQ", sequenceName="WORKOUT_SEQ", allocationSize=1)
	@GeneratedValue(generator="WORKOUT_SEQ", strategy=GenerationType.SEQUENCE)
	private int workout_id;
	
	private String workout_name;
	private int workout_duration;
	private String workout_muscle_group;
	private int workout_calories;
	
	//adding FK constraint on user_id?
	@JoinColumn(name="user_id")
	private User user;
	
	//no-args constructor
	public Workout() {
		super();
	}

	//full-args constructor
	public Workout(int workout_id, String workout_name, int workout_duration, String workout_muscle_group,
			int workout_calories, int user_id) {
		super();
		this.workout_id = workout_id;
		this.workout_name = workout_name;
		this.workout_duration = workout_duration;
		this.workout_muscle_group = workout_muscle_group;
		this.workout_calories = workout_calories;
		this.user_id = user_id;
	}

	//id-less constructor
	public Workout(String workout_name, int workout_duration, String workout_muscle_group, int workout_calories,
			int user_id) {
		super();
		this.workout_name = workout_name;
		this.workout_duration = workout_duration;
		this.workout_muscle_group = workout_muscle_group;
		this.workout_calories = workout_calories;
		this.user_id = user_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Workout [workout_id=" + workout_id + ", workout_name=" + workout_name + ", workout_duration="
				+ workout_duration + ", workout_muscle_group=" + workout_muscle_group + ", workout_calories="
				+ workout_calories + ", user_id=" + user_id + "]";
	}
	
	
	
	

}
