package com.dkang.iDoServer.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkang.iDoServer.model.TaskGroup;

public interface TaskGroupRepo extends JpaRepository<TaskGroup, Integer> {
	@Query("SELECT g FROM TaskGroup g JOIN g.ownerOfTaskGroup u WHERE u.userName = :uid")
	public Set<TaskGroup> findAllTaskGroupsOfUser(@Param("uid") String uid);
}
