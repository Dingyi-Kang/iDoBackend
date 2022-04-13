package com.dkang.iDoServer.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class UserTeamRelation {

	// primary key
	@Id
	@Column(name = "Relationship_ID")
	@GeneratedValue
	private int relationshipID;
	
	private Boolean adminStatus =  false;

	@ManyToOne
	@JoinColumn(name = "User_ID", referencedColumnName = "User_ID")
	User user;

	@ManyToOne
	@JoinColumn(name = "Team_ID", referencedColumnName = "Team_ID")
	Team team;

	@OneToMany(mappedBy = "assignedToUserTeamRelation", cascade = CascadeType.ALL)
	private Set<Task> hasTasks;

	public int getRelationshipID() {
		return relationshipID;
	}

	public void setRelationshipID(int relationshipID) {
		this.relationshipID = relationshipID;
	}

	public Boolean getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Boolean adminStatus) {
		this.adminStatus = adminStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
