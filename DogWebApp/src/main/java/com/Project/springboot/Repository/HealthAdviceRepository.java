package com.Project.springboot.Repository;

import com.Project.springboot.BaseClasses.HealthAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthAdviceRepository extends JpaRepository<HealthAdvice, Long> {

}