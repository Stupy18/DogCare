package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.Dog;
import com.Project.springboot.BaseClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByUser(User user);
    @Query("SELECT d.dogID FROM Dog d WHERE d.user = :user AND d.name = :name")
    Optional<Long> findDogIdByUserAndName(@Param("user") User user, @Param("name") String name);

}