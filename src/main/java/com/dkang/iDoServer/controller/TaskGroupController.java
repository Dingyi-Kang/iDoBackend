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

import com.dkang.iDoServer.model.TaskGroup;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.repo.TaskGroupRepo;
import com.dkang.iDoServer.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TaskGroupController {
	
	@Autowired
	private TaskGroupRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/groups")
	public List<TaskGroup> getAllTaskGroups(){
		return repo.findAll();
	}
	
	@PostMapping("/group")
	public TaskGroup addTask(@RequestBody TaskGroup taskGroup) {
		repo.save(taskGroup);
		return taskGroup;
	}
	
	@DeleteMapping("/group/{id}")
	public String deleteTask(@PathVariable int id) {
		Optional<TaskGroup> g = repo.findById(id);
		if (g.isEmpty()) {
			return "Deletion failed. No such record.";
		}else {
			TaskGroup g2 = g.orElse(null);
			String name = g2.getTaskGroupName();
			int gId = g2.getGroupID();
			repo.delete(g2);
			return name + " with ID of " + String.valueOf(gId) + " is deleted";
		}
	}

	@PostMapping("/group/{uid}")
	public Optional<TaskGroup> saveUpdateTaskGroupOfUser(@RequestBody TaskGroup taskGroup, @PathVariable String uid) {
		Optional<User> u = userRepo.findByUserName(uid);
		if (u.isEmpty()) {
				return null;
		} else {
			User u2 = u.orElse(null);
			taskGroup.setOwnerOfTaskGroup(u2);
			repo.save(taskGroup);
			return Optional.of(taskGroup);
		}
	}
	
	@GetMapping("/groups/{uid}")
	public Set<TaskGroup> getAllTaskGroupsOfUser(@PathVariable String uid) {
		return repo.findAllTaskGroupsOfUser(uid);
	}
	
}
