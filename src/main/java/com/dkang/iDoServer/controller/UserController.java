package com.dkang.iDoServer.controller;

import java.util.ArrayList;
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


import com.dkang.iDoServer.model.User;

import com.dkang.iDoServer.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserController {

	@Autowired
	private UserRepo repo;

	// RequestBody is needed, or cannot sent data in request. all parameters will be
	// null
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		repo.save(user);
		return user;
	}

	@GetMapping("/users")
	public List<String> getAllUsers() {
		List<String> IDs = new ArrayList<String>();
		List<User> users = repo.findAll();
		for (int i = 0; i < users.size(); i++) {
			IDs.add(users.get(i).getUserName());
		}
		return IDs;
	}

	// since it could be null, you should use the method findById instead of
	// getById. the first return an optional type
	@GetMapping("/user/{id}")
	public Optional<User> getOneUser(@PathVariable String id) {
		return repo.findByUserName(id);
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable String id) {
		Optional<User> u = repo.findByUserName(id);
		if (u.isEmpty()) {
			return "Deletion failed. No such record.";
		} else {
			User u2 = u.orElse(null);
			String uName = u2.getUserName();
			String uId = u2.getUserName();
			repo.delete(u2);
			return uName + " with ID of " + uId + " is deleted";
		}
	}

	@PutMapping("/user/{id}")
	public Optional<User> updateUser(@RequestBody User user, @PathVariable String id) {
		Optional<User> u = repo.findByUserName(id);
		if (u.isEmpty()) {
				return null;
		} else {
			User u2 = u.orElse(null);
			// u2.setUserName(user.getUserName());
			u2.setNickName(user.getNickName());
			u2.setPassword(user.getPassword());
			repo.save(u2);
			return Optional.of(u2);
		}

	}
	

}
