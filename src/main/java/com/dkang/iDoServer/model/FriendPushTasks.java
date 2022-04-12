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
public class FriendPushTasks {
	//primary key
		@Id
		@Column(name = "Relationship_ID")
		@GeneratedValue
		private int relationshipID;
		
		@ManyToOne
		@JoinColumn(name = "Sender_ID", referencedColumnName = "User_ID")
		User sender;
		
		@ManyToOne
		@JoinColumn(name = "Receiver_ID", referencedColumnName = "User_ID")
		User receiver;
		
		@OneToMany(mappedBy = "assignedToFriendPushTasks", cascade = CascadeType.ALL)
		private Set<Task> hasTasks;

		public int getRelationshipID() {
			return relationshipID;
		}

		public void setRelationshipID(int relationshipID) {
			this.relationshipID = relationshipID;
		}

		public User getSender() {
			return sender;
		}

		public void setSender(User sender) {
			this.sender = sender;
		}

		public User getReceiver() {
			return receiver;
		}

		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}
		
		
}
