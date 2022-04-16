package com.dkang.iDoServer.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Team {

	@Id
	@Column(name = "Team_ID")
	private String teamID;
	
	@NotNull
	private String teamName;
	
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	Set<UserTeamRelation> hasRelations;
	
		
	@OneToMany(mappedBy = "destinationTeam",cascade = CascadeType.ALL)
	Set<TeamInvitation> hasTeamInvitedToJoin;


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public String getTeamID() {
		return teamID;
	}


	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}


	
	
	
}
