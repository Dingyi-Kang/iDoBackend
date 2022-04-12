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
import javax.validation.constraints.NotNull;

@Entity
public class TaskGroup{
	
	//primary key
	@Id
	@Column(name = "TaskGroup_ID")
	@GeneratedValue
	private int groupID;
	
	@NotNull
	@Column(name = "Task_Group_Name")
	private String taskGroupName;
	
	@NotNull
	@Column()
	private String taskGroupColor;
	
	@ManyToOne
	@JoinColumn(name = "Owner_ID", referencedColumnName = "User_ID")
	User ownerOfTaskGroup;
		
	@OneToMany(mappedBy = "assignedToTaskGroup", cascade = CascadeType.ALL)
	private Set<Task> hasTasks;
	
	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getTaskGroupName() {
		return taskGroupName;
	}

	public void setTaskGroupName(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}

	public String getTaskGroupColor() {
		return taskGroupColor;
	}

	public void setTaskGroupColor(String taskGroupColor) {
		this.taskGroupColor = taskGroupColor;
	}

	public User getOwnerOfTaskGroup() {
		return ownerOfTaskGroup;
	}

	public void setOwnerOfTaskGroup(User ownerOfTaskGroup) {
		this.ownerOfTaskGroup = ownerOfTaskGroup;
	}
	
}
