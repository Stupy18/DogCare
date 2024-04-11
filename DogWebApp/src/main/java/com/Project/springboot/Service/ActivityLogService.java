package com.Project.springboot.Service;


import com.Project.springboot.BaseClasses.Activity;
import com.Project.springboot.BaseClasses.ActivityLog;
import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.Repository.ActivityLogRepository;
import com.Project.springboot.Repository.ActivityRepository;
import com.Project.springboot.Repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityLog addActivityToDog(Integer dogId, Integer activityId, Date timestamp, String notes) {
        Dog dog = dogRepository.findById(Long.valueOf(dogId)).orElseThrow(() -> new RuntimeException("Dog not found"));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));

        ActivityLog activityLog = new ActivityLog();
        activityLog.setDog(dog);
        activityLog.setActivity(activity);
        activityLog.setTimestamp(timestamp);
        activityLog.setNotes(notes);
        return activityLogRepository.save(activityLog);
    }

    public List<ActivityLog> getActivityLogsForDog(Integer dogId) {
        return activityLogRepository.findByDogId(dogId);
    }

    public List<ActivityLog> getAllActivityLogs() {
        return activityLogRepository.findAll();
    }

    // Additional methods like removing an activity or querying logs can be added here.
}