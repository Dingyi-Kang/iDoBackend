package com.dkang.iDoServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkang.iDoServer.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{

}
