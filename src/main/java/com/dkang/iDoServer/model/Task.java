package com.dkang.iDoServer.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	//copy instead of assign this one
//	@ManyToMany(mappedBy = "hasTasks")
//	Set<User> assignedToUsers;
	
	@ManyToOne
	@JoinColumn(name = "User_ID")
    User assignedToUser;
	
	@ManyToOne
	@JoinColumn(name = "TaskGroup_ID")
    TaskGroup assignedToTaskGroup;
		
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
	
	
	public User getAssignedToUser() {
		return assignedToUser;
	}
	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}
	
	public TaskGroup getAssignedToTaskGroup() {
		return assignedToTaskGroup;
	}
	public void setAssignedToTaskGroup(TaskGroup assignedToTaskGroup) {
		this.assignedToTaskGroup = assignedToTaskGroup;
	}
	
}
