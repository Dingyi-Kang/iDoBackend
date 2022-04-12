package com.dkang.iDoServer.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{
	@Query("SELECT t FROM Task t JOIN t.assignedToUser u WHERE u.userName = :uid")
	public Set<Task> findAllTasksOfUser(@Param("uid") String uid);
	
	
	@Query("SELECT t FROM Task t JOIN t.assignedToTaskGroup g JOIN g.ownerOfTaskGroup u WHERE u.userName = :uid AND g.groupID = :gid")
	public Set<Task> findAllTasksOfUserInAGroup(@Param("uid") String uid, @Param("gid") Integer gid);
	
	@Query(value="SELECT * FROM Task as t Where t.relationship_id = (Select relationship_id From Friend_Push_Tasks Where sender_id = :uid AND receiver_id = :fid)", nativeQuery=true)
	List<Task> getFriendTasksPushedByUser(@Param("uid") @PathVariable String uid, @Param("fid") @PathVariable String fid);
	
//	@Query(value="SELECT * FROM Task as t Where t.relationship_id = (Select relationship_id From Friend_Push_Tasks Where sender_id = :fid AND receiver_id = :uid)", nativeQuery=true)
//	List<Task> getFriendTasksReceivededByUser(@Param("uid") @PathVariable String uid);

}
