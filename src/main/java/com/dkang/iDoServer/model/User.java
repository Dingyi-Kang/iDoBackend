package com.dkang.iDoServer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class User {
	@Id
	@Column(name = "User_ID")
	private String userName;
	
	private String nickName;
	@NotNull
	private String password;

	@OneToMany(mappedBy = "assignedToUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Task> hasTasks;
	
	@OneToMany(mappedBy = "ownerOfTaskGroup", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<TaskGroup> taskGroups;
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	Set<FriendPushTasks> friendPushTaskRelationshipsAsSender;
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	Set<FriendPushTasks> friendPushTaskRelationshipsAsReceiver;
	
	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	Set<FriendInvitation> hasSentFriendInvitations;
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	Set<FriendInvitation> hasReceivedFriendInvitations;
	
	@OneToMany(mappedBy = "receiverOfTeamInvitation", cascade = CascadeType.ALL)
	Set<TeamInvitation> hasReceivedTeamInvitations;
	
	@OneToMany(mappedBy = "senderOfTeamInvitation", cascade = CascadeType.ALL)
	Set<TeamInvitation> hasSentTeamInvitations;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	Set<UserTeamRelation> isMemberOfTeams;
	
	@OneToMany(mappedBy = "ownerOfSavedLocations", cascade = CascadeType.ALL)
	Set<UserSavedLocations> savedLocations;

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
