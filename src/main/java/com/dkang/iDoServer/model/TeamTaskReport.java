package com.dkang.iDoServer.model;



public class TeamTaskReport {
	
//	private String teamID;
//	private String userName;
	private Integer count;
	
//	public String getTeamID() {
//		return teamID;
//	}
//	public void setTeamID(String teamID) {
//		this.teamID = teamID;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public TeamTaskReport(Integer count) {
		super();
		this.count = count;
	}
	
	

}
