package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Bus_Occupancy;

public interface BusOccupancyService {

    Bus_Occupancy saveBusOccupancy(Bus_Occupancy busOccupancy);

    List<Bus_Occupancy> getAllBusOccupancies();

    Optional<Bus_Occupancy> getBusOccupancyById(Long id);

    Bus_Occupancy updateBusOccupancy(Long id, Bus_Occupancy busOccupancy);

    void deleteBusOccupancy(Long id);

}