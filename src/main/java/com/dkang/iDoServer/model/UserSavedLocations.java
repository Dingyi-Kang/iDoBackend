package com.dkang.iDoServer.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class UserSavedLocations implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String locationAddr;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "Owner_ID", referencedColumnName = "User_ID")
	User ownerOfSavedLocations;

	@NotNull
	@Column(unique=true)
	private String tag;

	public String getLocationAddr() {
		return locationAddr;
	}

	public void setLocationAddr(String locationAddr) {
		this.locationAddr = locationAddr;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public User getOwnerOfSavedLocations() {
		return ownerOfSavedLocations;
	}

	public void setOwnerOfSavedLocations(User ownerOfSavedLocations) {
		this.ownerOfSavedLocations = ownerOfSavedLocations;
	}
	
	
}
