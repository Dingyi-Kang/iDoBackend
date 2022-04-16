package com.dkang.iDoServer.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import com.dkang.iDoServer.model.TeamInvitation;

public interface TeamInvitationRepo extends JpaRepository<TeamInvitation, Integer>  {

	@Query(value="SELECT * FROM Team_Invitation f WHERE f.sender_id = :uid AND f.team_id = :tid", nativeQuery=true)
	List<TeamInvitation> getAllSentInvitationsFromATeam(@Param("uid") @PathVariable String uid, @Param("tid") @PathVariable String tid);
	
	@Query("SELECT f FROM TeamInvitation f JOIN f.receiverOfTeamInvitation u WHERE u.userName = :uid AND f.invitationStatus = null")
	List<TeamInvitation> getAllReceivedTeamInvitations(@Param("uid") @PathVariable String uid);
	
}


