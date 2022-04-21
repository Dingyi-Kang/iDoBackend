package com.dkang.iDoServer.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.model.UserSavedLocation;
import com.dkang.iDoServer.repo.SavedLocationRepo;
import com.dkang.iDoServer.repo.UserRepo;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class LocationRelationController {

	@Autowired
	private SavedLocationRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/locations")
	public List<UserSavedLocation> getAllSavedLocations(){
		return repo.findAll();
	}
	
	@PostMapping("/locations/{uid}")
	public Optional<UserSavedLocation> saveLocationForUser(@RequestBody UserSavedLocation location, @PathVariable String uid) {
		Optional<User> u = userRepo.findByUserName(uid);
		if (u.isEmpty()) {
				return null;
		} else {
			User u2 = u.orElse(null);
			location.setOwnerOfSavedLocation(u2);
			repo.save(location);
			return Optional.of(location);
		}
	}	
	
	
	@DeleteMapping("/locations/{id}")
	public String deleteLocationForUser(@PathVariable int id) {
		Optional<UserSavedLocation> g = repo.findById(id);
		if (g.isEmpty()) {
			return "Deletion failed. No such record.";
		}else {
			UserSavedLocation g2 = g.orElse(null);
			String place = g2.getFormattedAddr();
			String name = g2.getOwnerOfSavedLocation().getUserName();
			repo.delete(g2);
			return place + " of " + name + " is deleted";
		}
	}
	
	@GetMapping("/locations/{uid}")
	public Set<UserSavedLocation> getAllLocationOfUser(@PathVariable String uid) {
		return repo.findAllLocationsOfUser(uid);
	}
	
	
}
