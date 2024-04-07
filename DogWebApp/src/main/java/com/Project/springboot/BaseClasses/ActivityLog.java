package com.Project.springboot.BaseClasses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter
@Entity
public class ActivityLog {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityID;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings
    @Column(nullable = false, length = 255)
    private ActivityType activityType;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date timestamp;

    @Setter
    @Getter
    @Column(nullable = true, length = 255)
    private String notes;

}
