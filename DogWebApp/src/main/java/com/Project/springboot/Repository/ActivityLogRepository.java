package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
    @Query("SELECT al FROM ActivityLog al WHERE al.dog.dogID = :dogId")
    List<ActivityLog> findByDogId(@Param("dogId") Integer dogId);
}