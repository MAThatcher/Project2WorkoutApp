package com.revature.beans;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//nick's comment

@Entity //Denotes the Class as having DB representation
@Table(name = "actors")
public class Actor {

	@Id
	@Column(updatable = false)
	@SequenceGenerator(name = "ACTOR_SEQ", sequenceName = "ACTOR_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "ACTOR_SEQ", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false, name = "name")
	private String name;

	private int worth;

	public Actor() {
		super();
	}

	public Actor(int id, String name, int worth) {
		super();
		this.id = id;
		this.name = name;
		this.worth = worth;
	}

	public Actor(String name, int worth) {
		super();
		this.name = name;
		this.worth = worth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", worth=" + worth + "]";
	}
	
}
