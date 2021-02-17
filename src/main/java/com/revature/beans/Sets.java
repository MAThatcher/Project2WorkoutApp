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
@Table(name = "sets")
public class Sets {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "SETS_SEQ", sequenceName = "SETS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "SETS_SEQ", strategy = GenerationType.SEQUENCE)
	private int set_id;
	
	@Column(name = "repetitions")
	private int repetitions;
	
	@Column(name = "total_calories")
	private int total_calories;
	
	@Column(name ="amount_sets")
	private int amount_sets;

	@ManyToOne
	@JoinColumn(name = "ex_id")
	private ExerciseReference exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id")
	private Workout workout;

    

	public Sets() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Sets(int set_id, int repetitions, int total_calories, int amount_sets, ExerciseReference exercise,
			Workout workout) {
		super();
		this.set_id = set_id;
		this.repetitions = repetitions;
		this.total_calories = total_calories;
		this.amount_sets = amount_sets;
		this.exercise = exercise;
		this.workout = workout;
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

	public ExerciseReference getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseReference exercise) {
		this.exercise = exercise;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public int getAmount_sets() {
		return amount_sets;
	}

	public void setAmount_sets(int amount_sets) {
		this.amount_sets = amount_sets;
	}

	@Override
	public String toString() {
		return "Sets [set_id=" + set_id + ", repetitions=" + repetitions + ", total_calories=" + total_calories
				+ ", amount_sets=" + amount_sets + ", exercise=" + exercise + ", workout=" + workout + "]";
	}

    
	

	
}
