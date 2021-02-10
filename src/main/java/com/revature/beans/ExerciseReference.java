package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EXERCISE_REFERENCE")
public class ExerciseReference {

	@Id
	@Column(updatable = false, name = "ex_id")
	@SequenceGenerator(name = "EXERCISE_REFERENCE_SEQ", sequenceName = "EXERCISE_REFERENCE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "EXERCISE_REFERENCE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id = 0;

	@Column(name = "ex_type")
	private String type;

	@Column(name = "ex_name")
	private String name;

	@Column(name = "ex_muscle_group")
	private String mGroup;

	@Column(name = "ex_difficulty")
	private String difficulty;

	@Column(name = "ex_calories")
	private int calories;

	@Column(name = "unit")
	private String unit;

	public ExerciseReference(int id, String type, String name, String mGroup, String difficulty, int calories,
			String unit) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.mGroup = mGroup;
		this.difficulty = difficulty;
		this.calories = calories;
		this.unit = unit;
	}

	public ExerciseReference() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmGroup() {
		return mGroup;
	}

	public void setmGroup(String mGroup) {
		this.mGroup = mGroup;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "ExerciseReference [id=" + id + ", type=" + type + ", name=" + name + ", mGroup=" + mGroup
				+ ", difficulty=" + difficulty + ", calories=" + calories + ", unit=" + unit + "]";
	}

}
