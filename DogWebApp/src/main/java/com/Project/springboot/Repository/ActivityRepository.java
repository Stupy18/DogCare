package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {}