package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.BaseClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByUser(User user);

}