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

@Entity
@Table(name = "WORKOUT")
public class Workout {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "WORKOUT_SEQ", sequenceName = "WORKOUT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "WORKOUT_SEQ", strategy = GenerationType.SEQUENCE)
	private int workout_id;

	@Column(name = "WORKOUT_NAME", unique = true)
	private String name;

	@Column(name = "WORKOUT_DURATION")
	private int duration;

	@Column(name = "WORKOUT_MUSCLE_GROUP")
	private String mGroup;

	@Column(name = "WORKOUT_CALORIES")
	private int calories;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	// no-args constructor
	public Workout() {
		super();
	}

	public Workout(int workout_id, String name, int duration, String mGroup, int calories, User user) {
		super();
		this.workout_id = workout_id;
		this.name = name;
		this.duration = duration;
		this.mGroup = mGroup;
		this.calories = calories;
		this.user = user;
	}

	public int getWorkout_id() {
		return workout_id;
	}

	public void setWorkout_id(int workout_id) {
		this.workout_id = workout_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getmGroup() {
		return mGroup;
	}

	public void setmGroup(String mGroup) {
		this.mGroup = mGroup;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Workout [workout_id=" + workout_id + ", name=" + name + ", duration=" + duration + ", mGroup=" + mGroup
				+ ", calories=" + calories + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + duration;
		result = prime * result + ((mGroup == null) ? 0 : mGroup.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + workout_id;
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
		Workout other = (Workout) obj;
		if (calories != other.calories)
			return false;
		if (duration != other.duration)
			return false;
		if (mGroup == null) {
			if (other.mGroup != null)
				return false;
		} else if (!mGroup.equals(other.mGroup))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (workout_id != other.workout_id)
			return false;
		return true;
	}
	
	

}
