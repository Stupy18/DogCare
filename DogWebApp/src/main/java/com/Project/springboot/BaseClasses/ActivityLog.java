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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "dogId", nullable = false)
    private Dog dog;

    @ManyToOne
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    @Column(nullable = false)
    private Date timestamp;

    @Column(length = 255)
    private String notes;
}
