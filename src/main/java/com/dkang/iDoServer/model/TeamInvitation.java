package com.dkang.iDoServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeamInvitation {
	
	@Id
	@Column(name = "Team_Invitation_ID")
	@GeneratedValue
	private int teamInvitationID;
	
	//null, accept, or reject. new invite is set to null. Select the null ones to display
	private Boolean invitationStatus =  null;
	
	@ManyToOne
    @JoinColumn(name = "team_ID")
    Team destinationTeam;
	
	@ManyToOne
    @JoinColumn(name = "receiver_id")
    User receiverOfTeamInvitation;
	
	@ManyToOne
    @JoinColumn(name = "sender_id")
    User senderOfTeamInvitation;

	public int getTeamInvitationID() {
		return teamInvitationID;
	}

	public void setTeamInvitationID(int teamInvitationID) {
		this.teamInvitationID = teamInvitationID;
	}

	public Boolean getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Boolean invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	public Team getDestinationTeam() {
		return destinationTeam;
	}

	public void setDestinationTeam(Team destinationTeam) {
		this.destinationTeam = destinationTeam;
	}

	public User getReceiverOfTeamInvitation() {
		return receiverOfTeamInvitation;
	}

	public void setReceiverOfTeamInvitation(User receiverOfTeamInvitation) {
		this.receiverOfTeamInvitation = receiverOfTeamInvitation;
	}

	public User getSenderOfTeamInvitation() {
		return senderOfTeamInvitation;
	}

	public void setSenderOfTeamInvitation(User senderOfTeamInvitation) {
		this.senderOfTeamInvitation = senderOfTeamInvitation;
	}

	

	
}
