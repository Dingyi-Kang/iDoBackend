package com.dkang.iDoServer.repo;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkang.iDoServer.model.UserSavedLocation;

public interface SavedLocationRepo extends JpaRepository<UserSavedLocation, Integer>{
	@Query("SELECT g FROM UserSavedLocation g JOIN g.ownerOfSavedLocation u WHERE u.userName = :uid")
	public Set<UserSavedLocation> findAllLocationsOfUser(@Param("uid") String uid);
}
