package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Real_Time_Tracking;

@Repository
public interface RealTimeTrackingRepository extends JpaRepository<Real_Time_Tracking, Long> {

}