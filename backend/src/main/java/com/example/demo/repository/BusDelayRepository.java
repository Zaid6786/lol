package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.BusDelay;

@Repository
public interface BusDelayRepository extends JpaRepository<BusDelay, Long> {

	Optional<BusDelay> findTopByBusIdAndStatusOrderByCreatedAtDesc(Long busId, BusDelay.DelayStatus status);

}