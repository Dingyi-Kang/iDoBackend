package com.dkang.iDoServer.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class UserGroupColor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "User_ID", referencedColumnName = "User_ID")
	User member;
	
	@Id
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "UserGroup_ID", referencedColumnName ="UserGroup_ID")
    UserGroup group;
	
	
	@Column(unique=true)
	private String color;



}
