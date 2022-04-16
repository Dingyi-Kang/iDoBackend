package com.dkang.iDoServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkang.iDoServer.model.Team;
import com.dkang.iDoServer.model.TeamTaskReport;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.model.UserTeamRelation;
import com.dkang.iDoServer.repo.TeamRepo;
import com.dkang.iDoServer.repo.UserRepo;
import com.dkang.iDoServer.repo.UserTeamRelationRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserTeamRelationController {

	
	@Autowired
	private UserTeamRelationRepo repo;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TeamRepo teamRepo;

	@GetMapping("/UserTeamRs")
	public List<UserTeamRelation> getAll(){
		return repo.findAll();
	}
	
	
	//for create non-admin member
	@PostMapping("/UserTeamR/{uid}/{tid}")
	public Optional<UserTeamRelation> saveUserTeamRelation(@PathVariable String uid,
			@PathVariable String tid) {
		Optional<User> u = userRepo.findByUserName(uid);
		Optional<Team> t = teamRepo.findByTeamID(tid);

		if (u.isEmpty() || t.isEmpty()) {
			return null;
		} else {
			UserTeamRelation ti = new UserTeamRelation();
			User u2 = u.orElse(null);
			Team t2 = t.orElse(null);
			ti.setTeam(t2);
			ti.setUser(u2);
			repo.save(ti);
			return Optional.of(ti);
		}
	}
	
	//for create admin member
	@PostMapping("/UserTeamR/admin/{uid}/{tid}")
	public Optional<UserTeamRelation> saveUserTeamRelationAsAdmin(@PathVariable String uid,
			@PathVariable String tid) {
		Optional<User> u = userRepo.findByUserName(uid);
		Optional<Team> t = teamRepo.findByTeamID(tid);

		if (u.isEmpty() || t.isEmpty()) {
			return null;
		} else {
			UserTeamRelation ti = new UserTeamRelation();
			User u2 = u.orElse(null);
			Team t2 = t.orElse(null);
			ti.setTeam(t2);
			ti.setUser(u2);
			ti.setAdminStatus(true);
			repo.save(ti);
			return Optional.of(ti);
		}
	}
	
	//for update admin status
//	@PostMapping("/UserTeamR/admin/{uid}/{tid}")
//	public UserTeamRelation updateUserTeamRelationAsAdmin(@PathVariable String uid,
//			@PathVariable String tid) {
//		Optional<UserTeamRelation> g = repo.getTheUserTeamRelation(uid, tid);
//		if (g.isEmpty()) {
//			return null;	
//		}
//		else {
//			UserTeamRelation g2 = g.orElse(null);	
//			g2.setAdminStatus(true);
//			repo.save(g2);
//			return g2;
//		}
//		
//	}
	
	@GetMapping("/UserTeamR/{uid}/{tid}")
	public Optional<UserTeamRelation> getFriendPushTaskRelation(@PathVariable String uid, @PathVariable String tid) {
		return repo.getUserTeamRelation(uid, tid);
	}

	
	@GetMapping("/UserTeamR/admin/{uid}/{tid}")
	public Boolean getAdminStatusFriendPushTaskRelation(@PathVariable String uid, @PathVariable String tid) {
		Optional<UserTeamRelation> r = repo.getUserTeamRelation(uid, tid);
		if (r.isEmpty()) {
			return false;	
		}
		else {
			UserTeamRelation r2 = r.orElse(null);
			return r2.getAdminStatus();
		}
	}
	
	
	

	@DeleteMapping("/UserTeamR/{uid}/{tid}")
	public String deleteUserTeamRelation(@PathVariable String uid, @PathVariable String tid) {
		Optional<UserTeamRelation> r = repo.getUserTeamRelation(uid, tid);
		if (r.isEmpty()) {
			return null;	
		}
		else {
			UserTeamRelation r2 = r.orElse(null);			
			repo.delete(r2);
			return "UserTeamRelation is deleted.";
		}

	}
	
	@GetMapping("/UserTeamR/report/{uid}/{tid}")
	public TeamTaskReport getReport(@PathVariable String uid, @PathVariable String tid) {
		return repo.getReport(uid, tid);
	}
	
}
