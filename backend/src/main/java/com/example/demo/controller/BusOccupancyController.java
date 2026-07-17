package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Bus_Occupancy;
import com.example.demo.service.BusOccupancyService;

@RestController
@RequestMapping("/busoccupancy")
@CrossOrigin(origins = "*")
public class BusOccupancyController {

    @Autowired
    private BusOccupancyService busOccupancyService;

    // Save Bus Occupancy
    @PostMapping("/save")
    public Bus_Occupancy saveBusOccupancy(@RequestBody Bus_Occupancy busOccupancy) {
        return busOccupancyService.saveBusOccupancy(busOccupancy);
    }

    // Get All Bus Occupancies
    @GetMapping("/getall")
    public List<Bus_Occupancy> getAllBusOccupancies() {
        return busOccupancyService.getAllBusOccupancies();
    }

    // Get Bus Occupancy By Id
    @GetMapping("/get/{id}")
    public Optional<Bus_Occupancy> getBusOccupancyById(@PathVariable Long id) {
        return busOccupancyService.getBusOccupancyById(id);
    }

    // Update Bus Occupancy
    @PutMapping("/update/{id}")
    public Bus_Occupancy updateBusOccupancy(@PathVariable Long id,
            @RequestBody Bus_Occupancy busOccupancy) {

        return busOccupancyService.updateBusOccupancy(id, busOccupancy);
    }

    // Delete Bus Occupancy
    @DeleteMapping("/delete/{id}")
    public String deleteBusOccupancy(@PathVariable Long id) {

        busOccupancyService.deleteBusOccupancy(id);
        return "Bus Occupancy Deleted Successfully";
    }

}