package com.dkang.iDoServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.TeamTaskReport;
import com.dkang.iDoServer.model.UserTeamRelation;



public interface UserTeamRelationRepo extends JpaRepository<UserTeamRelation, Integer>{
	
	@Query(value="SELECT * FROM User_Team_Relation r WHERE r.Team_ID = :tid AND r.User_ID = :uid", nativeQuery=true)
	Optional<UserTeamRelation> getUserTeamRelation(@Param("uid") @PathVariable String uid, @Param("tid") @PathVariable String tid);
	
	
	
	@Query(value="SELECT new com.dkang.iDoServer.model.TeamTaskReport(r.count as count) From CCTT r WHERE r.user_name = :uid AND r.team_ID = :tid")
	public TeamTaskReport getReport(@Param("uid") @PathVariable String uid, @Param("tid") @PathVariable String tid);
	
}
