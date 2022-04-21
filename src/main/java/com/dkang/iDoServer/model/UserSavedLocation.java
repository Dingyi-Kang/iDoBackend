package com.dkang.iDoServer.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class UserSavedLocation {
	
	@Id
	@Column(name = "Relation_ID")
	@GeneratedValue
	private int relationID;
	
	@NotNull
	private Double lat;
	
	@NotNull
	private Double lng;
	
	@NotNull
	private String formattedAddr;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "Owner_ID", referencedColumnName = "User_ID")
	User ownerOfSavedLocation;

	public int getRelationID() {
		return relationID;
	}

	public void setRelationID(int relationID) {
		this.relationID = relationID;
	}


	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public User getOwnerOfSavedLocation() {
		return ownerOfSavedLocation;
	}

	public void setOwnerOfSavedLocation(User ownerOfSavedLocation) {
		this.ownerOfSavedLocation = ownerOfSavedLocation;
	}

	public String getFormattedAddr() {
		return formattedAddr;
	}

	public void setFormattedAddr(String formattedAddr) {
		this.formattedAddr = formattedAddr;
	}

	
}
