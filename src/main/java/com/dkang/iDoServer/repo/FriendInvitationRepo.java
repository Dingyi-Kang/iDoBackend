package com.dkang.iDoServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.FriendInvitation;


public interface FriendInvitationRepo extends JpaRepository<FriendInvitation, Integer> {
	@Query("SELECT f FROM FriendInvitation f JOIN f.sender u WHERE u.userName = :uid")
	List<FriendInvitation> getAllSentFriendInvitations(@Param("uid") @PathVariable String uid);
	
	@Query("SELECT f FROM FriendInvitation f JOIN f.receiver u WHERE u.userName = :uid AND f.invitationStatus = null")
	List<FriendInvitation> getAllReceivedFriendInvitations(@Param("uid") @PathVariable String uid);
}
