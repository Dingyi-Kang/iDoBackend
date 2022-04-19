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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dkang.iDoServer.model.FriendPushTasks;
import com.dkang.iDoServer.model.Task;
import com.dkang.iDoServer.model.TaskGroup;
import com.dkang.iDoServer.model.User;
import com.dkang.iDoServer.model.UserTeamRelation;
import com.dkang.iDoServer.repo.FriendPushTasksRepo;
import com.dkang.iDoServer.repo.TaskGroupRepo;
import com.dkang.iDoServer.repo.TaskRepo;
import com.dkang.iDoServer.repo.UserRepo;
import com.dkang.iDoServer.repo.UserTeamRelationRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TaskController {

	@Autowired
	private TaskRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TaskGroupRepo groupRepo;
	
	@Autowired
	private FriendPushTasksRepo fptRepo;
	
	@Autowired
	private UserTeamRelationRepo utrRepo;
	
	//RequestBody is needed, or cannot sent data in request. all parameters will be null
	@PostMapping("/todo")
	public Task addTask(@RequestBody Task task) {
		repo.save(task);
		return task;
	}

	@GetMapping("/todos")
	public List<Task> getAllTasks(){
		return repo.findAll();
	}
	
	@GetMapping("/todo/{id}")
	public Optional<Task> getOneTask(@PathVariable int id) {
		return repo.findById(id);		
	}
	
	@DeleteMapping("/todo/{id}")
	public String deleteTask(@PathVariable int id) {
		Optional<Task> u = repo.findById(id);
		if (u.isEmpty()) {
			return "Deletion failed. No such record.";
		}else {
			Task u2 = u.orElse(null);
			String content = u2.getContent();
			int tId = u2.getTaskID();
			repo.delete(u2);
			return content + " with ID of " + String.valueOf(tId) + " is deleted";
		}
	}

	@PutMapping("/todo")
	public Task saveOrUpdateTask(@RequestBody Task task) {
		repo.save(task);
		return task;
	}
	
	//basic - post
	@PostMapping("/todo/{uid}")
	public Optional<Task> saveUpdateTaskOfUser(@RequestBody Task task, @PathVariable String uid) {
		Optional<User> u = userRepo.findByUserName(uid);
		if (u.isEmpty()) {
				return null;
		} else {
			User u2 = u.orElse(null);
			task.setAssignedToUser(u2);
			repo.save(task);
			return Optional.of(task);
		}
	}
	
	@GetMapping("/todos/{uid}")
	public Set<Task> getAllTasksOfUser(@PathVariable String uid) {
		return repo.findAllTasksOfUser(uid);
	}
	//including add and update(changing content and switch group)
	@PostMapping("/todo/{uid}/{gid}")
	public Optional<Task> saveUpdateTaskToUserGroup(@RequestBody Task task, @PathVariable String uid, @PathVariable Integer gid) {
		Optional<User> u = userRepo.findByUserName(uid);
		if (u.isEmpty()) {
				return null;
		} else {
			
			Optional<TaskGroup> g = groupRepo.findById(gid);
			
			if (g.isEmpty()) {
				return null;
			}else {
				User u2 = u.orElse(null);
				TaskGroup g2 = g.orElse(null);
				task.setAssignedToUser(u2);
				task.setAssignedToTaskGroup(g2);
				repo.save(task);
				return Optional.of(task);
			}
		}
	}
	
	@GetMapping("/todos/{uid}/{gid}")
	public Set<Task> getAllTasksOfUserInATaskGroup(@PathVariable String uid, @PathVariable Integer gid) {
		return repo.findAllTasksOfUserInAGroup(uid, gid);
	}
	
	//function -- adding task to FriendPushTaskRealtionship table
	//getting -- switch parameters for getting received friend push tasks
	@GetMapping("/pushTaskToFriend/{uid}/{fid}")
	public List<Task> getFriendTasksPushedByUser(@PathVariable String uid, @PathVariable String fid) {
		return repo.getFriendTasksPushedByUser(uid, fid);
	}
	//including add and update
	@PostMapping("/pushTaskToFriend/{uid}/{fid}")
	public Optional<Task> pushTaskToFriend(@RequestBody Task task, @PathVariable String uid, @PathVariable  String fid) {
		Optional<FriendPushTasks> u = fptRepo.getTheFriendPushRelation(fid, uid);
		if (u.isEmpty()) {
				return null;
		} else {
				FriendPushTasks u2 = u.orElse(null);
				task.setAssignedToFriendPushTasks(u2);
				repo.save(task);
				return Optional.of(task);
			
		}
	}
	
	//post and get from userTeamRelation
	@GetMapping("/userTeamRelation/{uid}/{tid}")
	public List<Task> getAllTasksOfUserInTeam(@PathVariable String uid, @PathVariable String tid) {
		return repo.getAllTasksOfUserInTeam(uid, tid);
	}
	//including add and update
	@PostMapping("/userTeamRelation/{uid}/{tid}")
	public Optional<Task> pushTaskInTeam(@RequestBody Task task, @PathVariable String uid, @PathVariable  String tid) {
		Optional<UserTeamRelation> u = utrRepo.getUserTeamRelation(uid, tid);
		if (u.isEmpty()) {
				return null;
		} else {
			UserTeamRelation u2 = u.orElse(null);
				task.setAssignedToUserTeamRelation(u2);
				repo.save(task);
				return Optional.of(task);
			
		}
	}
	
}
