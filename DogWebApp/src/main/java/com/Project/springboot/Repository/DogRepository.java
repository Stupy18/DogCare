package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.BaseClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByUser(User user);
    Optional<Dog> findByUserAndName(User user, String name); // New method

}