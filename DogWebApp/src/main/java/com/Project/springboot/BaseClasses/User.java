package com.Project.springboot.BaseClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
//import project.springbootHackaton.data.baseClasses.UserProfile;

@Getter
@Setter
@Entity
public class User {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Getter
    @Setter
    @Column(nullable = false, length = 255)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, length = 255)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;


    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date lastLogin;

    // One User can have multiple Dogs
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JsonIgnore
    private Set<Dog> dogs;




}