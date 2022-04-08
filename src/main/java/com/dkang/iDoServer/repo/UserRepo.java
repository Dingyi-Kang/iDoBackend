package com.dkang.iDoServer.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dkang.iDoServer.model.User;


//relative to crudRepo, Jpa implement it and further write and read data in JSON format 
public interface UserRepo extends JpaRepository<User, String>{

	//create custom query here
	//??? will this below work?
	Optional<User> findByUserName(String name);
	
}
