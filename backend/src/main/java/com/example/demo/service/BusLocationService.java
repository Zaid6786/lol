package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Bus_Location;

public interface BusLocationService {

    Bus_Location saveBusLocation(Bus_Location busLocation);

    List<Bus_Location> getAllBusLocations();

    Optional<Bus_Location> getBusLocationById(Long id);

    Bus_Location updateBusLocation(Long id, Bus_Location busLocation);

    void deleteBusLocation(Long id);

}