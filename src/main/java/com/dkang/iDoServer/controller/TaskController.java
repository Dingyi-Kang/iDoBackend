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

import com.dkang.iDoServer.model.Task;
import com.dkang.iDoServer.repo.TaskRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TaskController {

	@Autowired
	private TaskRepo repo;

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
	
}