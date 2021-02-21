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
	@Column(updatable = false, name = "EX_ID")
	@SequenceGenerator(name = "EXERCISE_REFERENCE_SEQ", sequenceName = "EXERCISE_REFERENCE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "EXERCISE_REFERENCE_SEQ", strategy = GenerationType.SEQUENCE)
	private int id = 0;

	@Column(name = "EX_TYPE")
	private String type;

	@Column(name = "EX_NAME")
	private String name;

	@Column(name = "EX_MUSCLE_GROUP")
	private String mGroup;

	@Column(name = "EX_DIFFICULTY")
	private String difficulty;

	@Column(name = "EX_CALORIES")
	private int calories;

	@Column(name = "UNIT")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + id;
		result = prime * result + ((mGroup == null) ? 0 : mGroup.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		ExerciseReference other = (ExerciseReference) obj;
		if (calories != other.calories)
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (id != other.id)
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
	

}
