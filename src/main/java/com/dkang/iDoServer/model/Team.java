package com.dkang.iDoServer.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Team {

	@Id
	@Column(name = "Team_ID")
	@GeneratedValue
	private int teamID;
	
	@NotNull
	private String teamName;
	
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	Set<UserTeamRelation> hasRelations;
	
		
	@OneToMany(mappedBy = "destinationTeam",cascade = CascadeType.ALL)
	Set<TeamInvitation> hasTeamInvitedToJoin;
	
	
	
}
