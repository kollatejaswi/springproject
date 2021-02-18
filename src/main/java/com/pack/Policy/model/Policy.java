package com.pack.Policy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private Type type;
	private long duration;
	private long amount;
	public Policy() {
		// TODO Auto-generated constructor stub
	}
	public Policy(long id, String name, Type type, long duration, long amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.duration = duration;
		this.amount = amount;
	}
	public Policy(String name, Type type, long duration, long amount) {
		super();
		this.name = name;
		this.type = type;
		this.duration = duration;
		this.amount = amount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
}
