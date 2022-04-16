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
@Table(name="CCTT")
//@Subselect("SELECT r.team_id as teamID, r.user_id as userName, Count(t.task_id) as count FROM Task t JOIN User_team_relation r ON t.team_relationship_id = r.relationship_id Group by r.team_id, r.user_id")
@Subselect("select s.teamID as team_ID, s.userName as user_name, s.count as count from cctt s")
public class CCTT implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
    String team_ID;
	
	@Id
    String user_name;
	
	@Column
    Integer count;
	
}
