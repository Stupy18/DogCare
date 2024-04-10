package com.Project.springboot.Controller;

import com.Project.springboot.BaseClasses.ActivityLog;
import com.Project.springboot.Dto.ActivityLogDto;
import com.Project.springboot.Service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity-logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    // Endpoint to add a new activity to a dog
    @PostMapping
    public ResponseEntity<ActivityLog> addActivityToDog(@RequestBody ActivityLogDto activityLogDto) {
        ActivityLog activityLog = activityLogService.addActivityToDog(
                activityLogDto.getDogId(),
                activityLogDto.getActivityId(),
                activityLogDto.getTimestamp(),
                activityLogDto.getNotes());
        return ResponseEntity.ok(activityLog);
    }

    // Endpoint to get all activity logs for a specific dog
    @GetMapping("/dog/{dogId}")
    public ResponseEntity<List<ActivityLog>> getActivityLogsForDog(@PathVariable Integer dogId) {
        List<ActivityLog> logs = activityLogService.getActivityLogsForDog(dogId);
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }
    @GetMapping
    public ResponseEntity<List<ActivityLog>> getAllActivityLogs() {
        List<ActivityLog> logs = activityLogService.getAllActivityLogs();
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    // Additional endpoints for deleting or updating activity logs can be implemented here
}
