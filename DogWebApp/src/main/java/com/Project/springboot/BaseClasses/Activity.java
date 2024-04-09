package com.Project.springboot.BaseClasses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityid;

    @Column(nullable = false, length = 255)
    private String name;
}
