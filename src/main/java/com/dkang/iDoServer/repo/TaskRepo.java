package com.dkang.iDoServer.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkang.iDoServer.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{
	@Query("SELECT t FROM Task t JOIN t.assignedToUser u WHERE u.userName = :uid")
	public Set<Task> findAllTasksOfUser(@Param("uid") String uid);
	
	
	@Query("SELECT t FROM Task t JOIN t.assignedToTaskGroup g JOIN g.ownerOfTaskGroup u WHERE u.userName = :uid AND g.groupID = :gid")
	public Set<Task> findAllTasksOfUserInAGroup(@Param("uid") String uid, @Param("gid") Integer gid);

}
