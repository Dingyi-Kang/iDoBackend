package com.dkang.iDoServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import com.dkang.iDoServer.model.FriendPushTasks;

public interface FriendPushTasksRepo extends JpaRepository<FriendPushTasks, Integer>{

	@Query("SELECT f FROM FriendPushTasks f JOIN f.receiver u JOIN f.sender u2 WHERE u.userName = :uid AND u2.userName = :fid")
	Optional<FriendPushTasks> getTheFriendPushRelation(@Param("uid") @PathVariable String uid, @Param("fid") @PathVariable String fid);
	
	//@Query(value="SELECT fp.receiver FROM FriendPushTasks fp JOIN User u On fp.sender == u.userName WHERE u.userName = :uid", nativeQuery=true)
//	@Query(value="SELECT new com.dkang.iDoServer.model.User(u.user_id, u.nick_name, u.password) FROM User u JOIN Friend_Push_Tasks f ON u.user_id=f.sender_id Where u.user_id = :uid", nativeQuery=true)
//	List<User> getAllFriendOfUser(@Param("uid") @PathVariable String uid); -- results cannot be converted into User object if not in the User repo
}
