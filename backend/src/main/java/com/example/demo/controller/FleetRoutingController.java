package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.fleet_routing;
import com.example.demo.service.FleetRoutingService;

@RestController
@RequestMapping("/fleetrouting")
@CrossOrigin(origins = "*")
public class FleetRoutingController {

    @Autowired
    private FleetRoutingService fleetRoutingService;

    // Save Fleet Routing
    @PostMapping("/save")
    public fleet_routing saveFleetRouting(@RequestBody fleet_routing fleetRouting) {
        return fleetRoutingService.saveFleetRouting(fleetRouting);
    }

    // Get All Fleet Routings
    @GetMapping("/getall")
    public List<fleet_routing> getAllFleetRoutings() {
        return fleetRoutingService.getAllFleetRoutings();
    }

    // Get Fleet Routing By Id
    @GetMapping("/get/{id}")
    public Optional<fleet_routing> getFleetRoutingById(@PathVariable Long id) {
        return fleetRoutingService.getFleetRoutingById(id);
    }

    // Update Fleet Routing
    @PutMapping("/update/{id}")
    public fleet_routing updateFleetRouting(@PathVariable Long id,
                                            @RequestBody fleet_routing fleetRouting) {

        return fleetRoutingService.updateFleetRouting(id, fleetRouting);
    }

    // Delete Fleet Routing
    @DeleteMapping("/delete/{id}")
    public String deleteFleetRouting(@PathVariable Long id) {

        fleetRoutingService.deleteFleetRouting(id);
        return "Fleet Routing Deleted Successfully";
    }

}