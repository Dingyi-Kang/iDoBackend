package com.dkang.iDoServer.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class OneTaskInUserTaskGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn()
	TaskGroup groupOfATaskOfAUser;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "Task_ID", referencedColumnName = "Task_Group_Name")
	Task taskOfUserInGroup;
	
}
