package com.dkang.iDoServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupInvitation {
	
	@Id
	@Column(name = "Group_Invitation_ID")
	@GeneratedValue
	private int groupInvitationID;
	
	//null, accept, or reject. new invite is set to null. Select the null ones to display
	private Boolean invitationStatus =  null;
	
	@ManyToOne
    @JoinColumn(name = "UserGroup_ID")
    UserGroup destinationGroup;
	
	@ManyToOne
    @JoinColumn(name = "receiver_id")
    User receiverOfGroupInvitation;

	public int getGroupInvitationID() {
		return groupInvitationID;
	}

	public void setGroupInvitationID(int groupInvitationID) {
		this.groupInvitationID = groupInvitationID;
	}

	

	public Boolean getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Boolean invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	
}
