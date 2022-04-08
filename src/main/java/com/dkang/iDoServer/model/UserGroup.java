package com.dkang.iDoServer.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class UserGroup {

	@Id
	@Column(name = "UserGroup_ID")
	@GeneratedValue
	private int userGroupID;
	
	@NotNull
	private String userGroupName;
	
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	Set<UserGroupColor> hasMembers;
	
//	@ManyToMany
//	@JoinTable(
//			name = "group_has_user",
//			joinColumns = @JoinColumn(name = "UserGroup_ID"),
//			inverseJoinColumns = @JoinColumn(name = "User_ID")
//			)
//	Set<User> hasMembers;
	
	@ManyToMany
	@JoinTable(
			name = "group_has_task",
			joinColumns = @JoinColumn(name = "UserGroup_ID"),
			inverseJoinColumns = @JoinColumn(name = "Task_ID")
			)
	Set<Task> hasTasks;
	
	@OneToMany(mappedBy = "destinationGroup",cascade = CascadeType.ALL)
	Set<GroupInvitation> hasUsersInvitedToJoin;
	
	
	
	public int getUserGroupID() {
		return userGroupID;
	}
	
	public void setUserGroupID(int userGroupID) {
		this.userGroupID = userGroupID;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	
	
}
