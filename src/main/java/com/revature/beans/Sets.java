package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sets")
public class Sets {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "SETS_SEQ", sequenceName = "SETS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "SETS_SEQ", strategy = GenerationType.SEQUENCE)
	int set_id;
	
	@Column(name = "repetitions")
	int repetitions;
	
	@Column(name = "total_calories")
	int total_calories;
	
	@Column(name = "ex_id")
	int ex_id;
	
	@Column(name = "workout_id")
	int workout_id;
	
	public Sets() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sets(int set_id, int repetitions, int total_calories, int ex_id, int workout_id) {
		super();
		this.set_id = set_id;
		this.repetitions = repetitions;
		this.total_calories = total_calories;
		this.ex_id = ex_id;
		this.workout_id = workout_id;
	}
	public int getSet_id() {
		return set_id;
	}
	public void setSet_id(int set_id) {
		this.set_id = set_id;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}
	public int getTotal_calories() {
		return total_calories;
	}
	public void setTotal_calories(int total_calories) {
		this.total_calories = total_calories;
	}
	public int getEx_id() {
		return ex_id;
	}
	public void setEx_id(int ex_id) {
		this.ex_id = ex_id;
	}
	public int getWorkout_id() {
		return workout_id;
	}
	public void setWorkout_id(int workout_id) {
		this.workout_id = workout_id;
	}
	@Override
	public String toString() {
		return "Sets [set_id=" + set_id + ", repetitions=" + repetitions + ", total_calories=" + total_calories
				+ ", ex_id=" + ex_id + ", workout_id=" + workout_id + "]";
	}
	
}
