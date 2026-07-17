package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Bus_Delay;
import com.example.demo.service.BusDelayService;

@RestController
@RequestMapping("/busdelay")
@CrossOrigin(origins = "*")
public class BusDelayController {

    @Autowired
    private BusDelayService busDelayService;

    // Save Bus Delay
    @PostMapping("/save")
    public Bus_Delay saveBusDelay(@RequestBody Bus_Delay busDelay) {
        return busDelayService.saveBusDelay(busDelay);
    }

    // Get All Bus Delays
    @GetMapping("/getall")
    public List<Bus_Delay> getAllBusDelays() {
        return busDelayService.getAllBusDelays();
    }

    // Get Bus Delay By Id
    @GetMapping("/get/{id}")
    public Optional<Bus_Delay> getBusDelayById(@PathVariable Long id) {
        return busDelayService.getBusDelayById(id);
    }

    // Update Bus Delay
    @PutMapping("/update/{id}")
    public Bus_Delay updateBusDelay(@PathVariable Long id,
                                    @RequestBody Bus_Delay busDelay) {

        return busDelayService.updateBusDelay(id, busDelay);
    }

    // Delete Bus Delay
    @DeleteMapping("/delete/{id}")
    public String deleteBusDelay(@PathVariable Long id) {

        busDelayService.deleteBusDelay(id);
        return "Bus Delay Deleted Successfully";
    }

}