package com.dkang.iDoServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dkang.iDoServer.model.Team;
import com.dkang.iDoServer.repo.TeamRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TeamController {
	
	@Autowired
	private TeamRepo repo;

	
	@PostMapping("/team")
	public Team addTeam(@RequestBody Team team) {
		repo.save(team);
		return team;
	}
	
	@GetMapping("/team/{id}")
	public Optional<Team> getOneTeam(@PathVariable String id) {
		return repo.findById(id);
	}
	
	@GetMapping("/teams")
	public List<Team> getAllTeam() {
		return repo.findAll();
	}
	
	@DeleteMapping("/teams")
	public void deleteAllTeam() {
		repo.deleteAll();
	}
	
	@DeleteMapping("/team/{id}")
	public String deleteTeam(@PathVariable String id) {
		Optional<Team> u = Optional.of(repo.getById(id));
		if (u.isEmpty()) {
			return "Deletion failed. No such record.";
		} else {
			Team u2 = u.orElse(null);
			String uName = u2.getTeamName();
			repo.delete(u2);
			return uName + " is deleted";
		}
	}
	
	@GetMapping("/{uid}/teams")
	public List<Team> getAllFriendsOfUser(@PathVariable String uid) {
		return repo.getAllTeamsOfUser(uid);
	}
	
}
