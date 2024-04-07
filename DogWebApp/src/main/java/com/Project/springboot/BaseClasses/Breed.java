//package com.Project.springboot.BaseClasses;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//public class Breed {
//
//    @Id
//    @Setter
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer breedID;
//
//    @Setter
//    @Getter
//    @Column(nullable = false, length = 255)
//    private String breedName;
//
//    @Setter
//    @Getter
//    @Column(nullable = true, length = 255)
//    private Integer averageWeight;
//
//    @Setter
//    @Getter
//    @Column(nullable = true, length = 255)
//    private Float averageHeight;
//
//    @Setter
//    @Getter
//    @Column(nullable = true, length = 255)
//    private Integer lifeExpectancy;
//
//    @Column(nullable = true, length = 255)
//    private String breedSpecificAdvice;
//
//    // Dogs relationship
//    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL)
//    private Set<Dog> dogs;
//
//
//
//
//}
