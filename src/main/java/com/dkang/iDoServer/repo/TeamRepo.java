package com.dkang.iDoServer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.Team;


public interface TeamRepo extends JpaRepository<Team, String>{
	
	Optional<Team> findByTeamID(String teamID);
	
	@Query(value="SELECT * FROM Team as t Join User_Team_Relation r ON t.team_id = r.team_id Where r.user_id = :uid", nativeQuery=true)
	List<Team> getAllTeamsOfUser(@Param("uid") @PathVariable String uid);
}
