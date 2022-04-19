package com.dkang.iDoServer.model;



public class TeamTaskReport {
	
	private String userName;
		
	
//	private Integer countOfCompletedTasks;
//	
//	private Integer countOfUncompletedTasks;
	
	
	private Integer count;
		

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public TeamTaskReport(String userName, Integer count) {
		super();
		this.userName = userName;
		this.count = count;
	}



}
