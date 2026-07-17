package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Real_Time_Tracking;
import com.example.demo.service.RealTimeTrackingService;

@RestController
@RequestMapping("/realtimetracking")
@CrossOrigin(origins = "*")
public class RealTimeTrackingController {

    @Autowired
    private RealTimeTrackingService realTimeTrackingService;

    // Save Real Time Tracking
    @PostMapping("/save")
    public Real_Time_Tracking saveRealTimeTracking(
            @RequestBody Real_Time_Tracking realTimeTracking) {

        return realTimeTrackingService.saveRealTimeTracking(realTimeTracking);
    }

    // Get All Real Time Tracking Records
    @GetMapping("/getall")
    public List<Real_Time_Tracking> getAllRealTimeTrackings() {

        return realTimeTrackingService.getAllRealTimeTrackings();
    }

    // Get Real Time Tracking By Id
    @GetMapping("/get/{id}")
    public Optional<Real_Time_Tracking> getRealTimeTrackingById(
            @PathVariable Long id) {

        return realTimeTrackingService.getRealTimeTrackingById(id);
    }

    // Update Real Time Tracking
    @PutMapping("/update/{id}")
    public Real_Time_Tracking updateRealTimeTracking(
            @PathVariable Long id,
            @RequestBody Real_Time_Tracking realTimeTracking) {

        return realTimeTrackingService.updateRealTimeTracking(id, realTimeTracking);
    }

    // Delete Real Time Tracking
    @DeleteMapping("/delete/{id}")
    public String deleteRealTimeTracking(@PathVariable Long id) {

        realTimeTrackingService.deleteRealTimeTracking(id);
        return "Real Time Tracking Deleted Successfully";
    }

}