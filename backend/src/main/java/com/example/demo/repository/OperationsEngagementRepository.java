package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Operations_Engagement;

@Repository
public interface OperationsEngagementRepository extends JpaRepository<Operations_Engagement, Long> {

}