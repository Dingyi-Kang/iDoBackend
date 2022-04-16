package com.dkang.iDoServer.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.User;


//relative to crudRepo, Jpa implement it and further write and read data in JSON format 
public interface UserRepo extends JpaRepository<User, String>{
	//this below work!
	Optional<User> findByUserName(String name);
	
	@Query(value="SELECT * FROM User as u Where u.user_id IN (Select receiver_id From Friend_Push_Tasks Where sender_id = :uid)", nativeQuery=true)
	List<User> getAllFriendOfUser(@Param("uid") @PathVariable String uid);
	
	@Query(value="SELECT * FROM User u Join User_Team_Relation r ON u.user_id = r.user_id Where r.team_id = :tid", nativeQuery=true)
	List<User> getAllTeamMembers(@Param("tid") @PathVariable String tid);
	
}
