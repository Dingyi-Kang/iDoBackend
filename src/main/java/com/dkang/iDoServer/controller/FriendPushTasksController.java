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
import com.dkang.iDoServer.model.FriendPushTasks;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.repo.FriendPushTasksRepo;
import com.dkang.iDoServer.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class FriendPushTasksController {

	@Autowired
	private FriendPushTasksRepo repo;

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/FriendPushRs")
	public List<FriendPushTasks> getAll(){
		return repo.findAll();
	}
	
	// if create, create pairs;
	// if delete, delete pairs
	@PostMapping("/FriendPushR/{sid}/{rid}")
	public Optional<FriendPushTasks> savePushTasksRelationForSender(@PathVariable String sid,
			@PathVariable String rid) {
		Optional<User> sender = userRepo.findByUserName(sid);
		Optional<User> receiver = userRepo.findByUserName(rid);

		if (sender.isEmpty() || receiver.isEmpty()) {
			return null;
		} else {
			FriendPushTasks fi = new FriendPushTasks();
			User sender2 = sender.orElse(null);
			User receiver2 = receiver.orElse(null);
			fi.setSender(sender2);
			fi.setReceiver(receiver2);
			repo.save(fi);

			FriendPushTasks fi2 = new FriendPushTasks();
			fi2.setSender(receiver2);
			fi2.setReceiver(sender2);
			repo.save(fi2);

			return Optional.of(fi);
		}
	}

	// in future, we may use advanced query.

//	@GetMapping("/FriendPushR/sender/{uid}")
//	public List<FriendPushTasks> getAllFriendPushRelationsAsSender(@PathVariable String uid) {
//		return repo.getAllFriendPushRelationsAsSender(uid);
//	}
//
//	@GetMapping("/FriendPushR/receiver/{uid}")
//	public List<FriendPushTasks> getAllFriendPushRelationsAsReceiver(@PathVariable String uid) {
//		return repo.getAllFriendPushRelationsAsReceiver(uid);
//	}
	
	
	//a helper function to find opposite relation based sender id and receiver id
	//can use this function twice by switching the parameters
	@GetMapping("/FriendPushR/{uid}/{fid}")
	public Optional<FriendPushTasks> getFriendPushTaskRelation(@PathVariable String uid, @PathVariable String fid) {
		return repo.getTheFriendPushRelation(uid, fid);
	}

	// if delete, delete pairs
	// if create, create pairs;
	@DeleteMapping("/FriendPushR/{uid}/{fid}")
	public String deleteFriendRelationship(@PathVariable String uid, @PathVariable String fid) {
		Optional<FriendPushTasks> g = repo.getTheFriendPushRelation(uid, fid);
		Optional<FriendPushTasks> f = repo.getTheFriendPushRelation(fid, uid);		
		if (g.isEmpty()||f.isEmpty()) {
			return null;	
		}
		else {
			FriendPushTasks g2 = g.orElse(null);
			FriendPushTasks f2 = f.orElse(null);
			repo.delete(g2);
			repo.delete(f2);
			return "FriendPushTaskRelation is deleted.";
		}

	}

	

}
