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

import com.dkang.iDoServer.model.FriendInvitation;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.repo.FriendInvitationRepo;
import com.dkang.iDoServer.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class FriendInvitationController {

	
	@Autowired
	private FriendInvitationRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/fiv/sender/{uid}")
	public List<FriendInvitation> getAllSentFriendInvitations(@PathVariable String uid){
		return repo.getAllSentFriendInvitations(uid);
	}
	
	@GetMapping("/fiv/receiver/{uid}")
	public List<FriendInvitation> getAllReceivedFriendInvitations(@PathVariable String uid){
		return repo.getAllReceivedFriendInvitations(uid);
	}
	
	@PostMapping("/fiv/{sid}/{rid}")
	public Optional<FriendInvitation> addFriendInviation(@PathVariable String sid, @PathVariable String rid) {
		
		
		
		Optional<User> sender = userRepo.findByUserName(sid);
		Optional<User> receiver = userRepo.findByUserName(rid);
		
		if (sender.isEmpty()||receiver.isEmpty()) {
			return null;
		}else {
			FriendInvitation fi = new FriendInvitation();
			User sender2 = sender.orElse(null);
			User receiver2 = receiver.orElse(null);
			fi.setSender(sender2);
			fi.setReceiver(receiver2);
			repo.save(fi);
			return Optional.of(fi);
			
		}
	}
	
	
	@DeleteMapping("/fiv/{id}")
	public String deleteTask(@PathVariable int id) {
		Optional<FriendInvitation> g = repo.findById(id);
		if (g.isEmpty()) {
			return "Deletion failed. No such record.";
		}else {
			FriendInvitation g2 = g.orElse(null);
			String sender = g2.getSender().getUserName();
			String receiver = g2.getReceiver().getUserName();
			repo.delete(g2);
			return "FriendInvitation from "+sender+" to "+receiver+ " is deleted.";
		}
	}
	
	@PutMapping("/fiv")
	public FriendInvitation updateFriendInvitation(@RequestBody FriendInvitation fiv) {
		repo.save(fiv);
		return fiv;
	}
}
