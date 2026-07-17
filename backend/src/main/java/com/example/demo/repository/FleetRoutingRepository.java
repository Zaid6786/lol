package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.fleet_routing;

@Repository
public interface FleetRoutingRepository extends JpaRepository<fleet_routing, Long> {

}