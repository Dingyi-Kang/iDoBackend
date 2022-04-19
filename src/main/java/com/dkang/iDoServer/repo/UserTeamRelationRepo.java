package com.dkang.iDoServer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.dkang.iDoServer.model.TeamTaskReport;
import com.dkang.iDoServer.model.UserTeamRelation;



public interface UserTeamRelationRepo extends JpaRepository<UserTeamRelation, Integer>{
	
	@Query(value="SELECT * FROM User_Team_Relation r WHERE r.Team_ID = :tid AND r.User_ID = :uid", nativeQuery=true)
	Optional<UserTeamRelation> getUserTeamRelation(@Param("uid") @PathVariable String uid, @Param("tid") @PathVariable String tid);
	
	@Query(value="SELECT * FROM User_Team_Relation r WHERE r.Team_ID = :tid", nativeQuery=true)
	List<UserTeamRelation> getUserTeamRelationOfATeam(@Param("tid") @PathVariable String tid);
	
	
//	@Query(value="SELECT new com.dkang.iDoServer.model.TeamTaskReport(r.user_name as userName, r.count as countOfCompletedTasks, r2.count as countOfUncompletedTasks) From CCTT r, CUTT r2 WHERE r.user_name = :uid AND r.team_ID = :tid AND r2.user_name = :uid AND r2.team_ID = :tid")
//	public TeamTaskReport getReport(@Param("uid") @PathVariable String uid, @Param("tid") @PathVariable String tid);
//	
//	
//	@Query("SELECT new com.dkang.iDoServer.model.TeamTaskReport(r.user_name as userName, r.ccount as countOfCompletedTasks, r.ucount as countOfUncompletedTasks) From Report r WHERE r.team_ID = :tid")
//	List<TeamTaskReport> getReportOfTeam(@Param("tid") @PathVariable String tid);
	
	
	@Query("SELECT new com.dkang.iDoServer.model.TeamTaskReport(r.user_name as userName, r.count as count) From CCTT r WHERE r.team_ID = :tid")
	List<TeamTaskReport> getCompletedTasksCountForEachUsersInTeam(@Param("tid") @PathVariable String tid);
	
	@Query("SELECT new com.dkang.iDoServer.model.TeamTaskReport(r.user_name as userName, r.count as count) From CUTT r WHERE r.team_ID = :tid")
	List<TeamTaskReport> getUncompletedTasksCountForEachUsersInTeam(@Param("tid") @PathVariable String tid);
	
//	@GetMapping("/UserTeamR/report/{tid}")
//	public List<TeamTaskReport> getReportOfTeam(@PathVariable String tid) {
//		return repo.getReportOfTeam(tid);
//	}
//	
}
