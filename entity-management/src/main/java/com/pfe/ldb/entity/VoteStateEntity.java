package com.pfe.ldb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="voteStateEntity")
public class VoteStateEntity extends ModelEntity {

	private String name;
	private String description;
	

	public VoteStateEntity() {
		
	}
	
	public VoteStateEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}