package com.Project.springboot.BaseClasses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HealthAdvice {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adviceID;

    @Getter
    @Setter
    @Column(nullable = true, length = 255)
    private String adviceText;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings
    @Column(nullable = false, length = 255)
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "dogID", nullable = false)
    private Dog dog;



}
