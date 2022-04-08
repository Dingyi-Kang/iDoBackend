package com.dkang.iDoServer.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class TaskGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	//composite primary key
	@Id
	@Column(name = "Task_Group_Name")
	private String taskGroupName;
	
//	This won't work. cannot build a connect between this one and the foreigh key
//	@Id
//	@Column(name = "Owner_ID")
//	private int userID;
	
	@NotNull
	@Column(unique=true)
	private String taskGroupColor;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "Owner_ID", referencedColumnName = "User_ID")
	User ownerOfTaskGroup;
	
	@OneToMany(mappedBy = "groupOfATaskOfAUser", cascade = CascadeType.ALL)
	Set<OneTaskInUserTaskGroup> tasksOfUser;
	
}
