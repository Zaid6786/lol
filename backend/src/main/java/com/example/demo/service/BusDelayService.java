package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Bus_Delay;

public interface BusDelayService {

    Bus_Delay saveBusDelay(Bus_Delay busDelay);

    List<Bus_Delay> getAllBusDelays();

    Optional<Bus_Delay> getBusDelayById(Long id);

    Bus_Delay updateBusDelay(Long id, Bus_Delay busDelay);

    void deleteBusDelay(Long id);

}