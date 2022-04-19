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
@Table(name="CUTT")
@Subselect("select s.teamID as team_ID, s.userName as user_name, s.count as count from cutt s")
public class CUTT implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
    String team_ID;
	
	@Id
    String user_name;
	
	@Column
    Integer count=0;
	
	
}
