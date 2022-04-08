package com.dkang.iDoServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FriendInvitation {

	@Id
	@Column(name = "Friend_Invitation_ID")
	@GeneratedValue
	private int friendInvitationID;
	
	@ManyToOne
    @JoinColumn(name = "sender_id")
    User sender;
	
	@ManyToOne
    @JoinColumn(name = "receiver_id")
    User receiver;
	
	//null, accept, or reject. new invite is set to null. Select the null ones to display
	private Boolean invitationStatus = null;

	//no need time for filter. we can use ID number to distinguish old and new one
	//	@NotNull
//	private String sentTime;

	public int getFriendInvitationID() {
		return friendInvitationID;
	}

	public void setFriendInvitationID(int friendInvitationID) {
		this.friendInvitationID = friendInvitationID;
	}

	public Boolean getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Boolean invitationStatus) {
		this.invitationStatus = invitationStatus;
	}	
	
}
