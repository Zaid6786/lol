package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Bus_Occupancy;

@Repository
public interface BusOccupancyRepository extends JpaRepository<Bus_Occupancy, Long> {

}