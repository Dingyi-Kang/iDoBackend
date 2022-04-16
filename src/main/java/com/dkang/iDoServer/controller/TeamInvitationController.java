package com.dkang.iDoServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dkang.iDoServer.model.Team;
import com.dkang.iDoServer.model.TeamInvitation;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.repo.TeamInvitationRepo;
import com.dkang.iDoServer.repo.TeamRepo;
import com.dkang.iDoServer.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TeamInvitationController {
	
	@Autowired
	private TeamInvitationRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TeamRepo teamRepo;
	
	@GetMapping("/tiv/sender/{uid}/{tid}")
	public List<TeamInvitation> getAllSentInvitationsFromATeam(@PathVariable String uid, @PathVariable String tid){
		return repo.getAllSentInvitationsFromATeam(uid, tid);
	}
	
	@GetMapping("/tiv/receiver/{uid}")
	public List<TeamInvitation> getAllReceivedTeamInvitations(@PathVariable String uid){
		return repo.getAllReceivedTeamInvitations(uid);
	}
	
	@PostMapping("/tiv/{sid}/{rid}/{tid}")
	public Optional<TeamInvitation> addTeamInviation(@PathVariable String sid, @PathVariable String rid, @PathVariable String tid) {
		
		Optional<User> sender = userRepo.findByUserName(sid);
		Optional<User> receiver = userRepo.findByUserName(rid);
		Optional<Team> team = teamRepo.findByTeamID(tid);
		
		if (sender.isEmpty()||receiver.isEmpty()||team.isEmpty()) {
			return null;
		}else {
			TeamInvitation ti = new TeamInvitation();
			User sender2 = sender.orElse(null);
			User receiver2 = receiver.orElse(null);
			Team team2 = team.orElse(null);
			ti.setReceiverOfTeamInvitation(receiver2);
			ti.setSenderOfTeamInvitation(sender2);
			ti.setDestinationTeam(team2);
			repo.save(ti);
			return Optional.of(ti);
			
		}
	}
	
	@DeleteMapping("/tiv/{id}")
	public String deleteInvitation(@PathVariable int id) {
		Optional<TeamInvitation> g = repo.findById(id);
		if (g.isEmpty()) {
			return "Deletion failed. No such record.";
		}else {
			TeamInvitation g2 = g.orElse(null);
			String sender = g2.getSenderOfTeamInvitation().getUserName();
			String receiver = g2.getReceiverOfTeamInvitation().getUserName();
			repo.delete(g2);
			return "TeamInvitation from "+sender+" to "+receiver+ " is deleted.";
		}
	}
	
	@PutMapping("/tiv")
	public TeamInvitation updateTeamInvitation(@RequestBody TeamInvitation tiv) {
		repo.save(tiv);
		return tiv;
	}

}
