package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.fleet_routing;

public interface FleetRoutingService {

    fleet_routing saveFleetRouting(fleet_routing fleetRouting);

    List<fleet_routing> getAllFleetRoutings();

    Optional<fleet_routing> getFleetRoutingById(Long id);

    fleet_routing updateFleetRouting(Long id, fleet_routing fleetRouting);

    void deleteFleetRouting(Long id);

}