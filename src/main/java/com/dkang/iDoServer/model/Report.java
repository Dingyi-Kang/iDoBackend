package com.dkang.iDoServer.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Table(name="Report")
@Subselect("select s.teamID as team_ID, s.userName as user_name, s.countOfCompletedTasks as ccount, s.countOfUncompletedTasks as ucount from Report s")
public class Report implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
    String team_ID;
	
	@Id
    String user_name;
	
	@Column
    Integer ccount;
	
	@Column
    Integer ucount;
	
	
}
