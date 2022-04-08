package com.dkang.iDoServer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
//import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Task {
	@Id
	@Column(name = "Task_ID")
	@GeneratedValue
	private int taskID;

	private String dueTime;
	//has default value
	@NotNull
	private Boolean completed = false;
	@NotNull
	private String content;
	
	private String locationAddr;
	
	
	@ManyToMany(mappedBy = "hasTasks")
	Set<User> assignedToUsers;
	
//	@ManyToOne
//    @JoinColumn(name = "User_ID")
//    User assignedToUser;
	
	@OneToMany(mappedBy = "taskOfUserInGroup", cascade = CascadeType.ALL)
	Set<OneTaskInUserTaskGroup> isInTaskGroupsOfUser;
	
	@ManyToMany(mappedBy = "hasTasks")
	Set<UserGroup> isInUserGroups;
	
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocationAddr() {
		return locationAddr;
	}
	public void setLocationAddr(String locationAddr) {
		this.locationAddr = locationAddr;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
		
}