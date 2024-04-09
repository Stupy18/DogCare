package com.Project.springboot.Dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ActivityLogDto {

    // Getters and Setters
    private Integer dogId;
    private Integer activityId;
    private Date timestamp;
    private String notes;

    // Default constructor
    public ActivityLogDto() {
    }

    // Constructor with all fields
    public ActivityLogDto(Integer dogId, Integer activityId, Date timestamp, String notes) {
        this.dogId = dogId;
        this.activityId = activityId;
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // toString() method for logging or debugging purposes
    @Override
    public String toString() {
        return "ActivityLogDto{" +
                "dogId=" + dogId +
                ", activityId=" + activityId +
                ", timestamp=" + timestamp +
                ", notes='" + notes + '\'' +
                '}';
    }
}
