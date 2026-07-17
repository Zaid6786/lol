package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Bus_Delay;
import com.example.demo.repository.BusDelayRepository;
import com.example.demo.service.BusDelayService;

@Service
public class BusDelayServiceImple implements BusDelayService {

    @Autowired
    private BusDelayRepository busDelayRepository;

    @Override
    public Bus_Delay saveBusDelay(Bus_Delay busDelay) {
        return busDelayRepository.save(busDelay);
    }

    @Override
    public List<Bus_Delay> getAllBusDelays() {
        return busDelayRepository.findAll();
    }

    @Override
    public Optional<Bus_Delay> getBusDelayById(Long id) {
        return busDelayRepository.findById(id);
    }

    @Override
    public Bus_Delay updateBusDelay(Long id, Bus_Delay busDelay) {

        Bus_Delay existingBusDelay =
                busDelayRepository.findById(id).orElse(null);

        if (existingBusDelay != null) {

            existingBusDelay.setBusId(busDelay.getBusId());
            existingBusDelay.setReason(busDelay.getReason());
            existingBusDelay.setDelayMinutes(busDelay.getDelayMinutes());
            existingBusDelay.setStatus(busDelay.getStatus());
            existingBusDelay.setCreatedAt(busDelay.getCreatedAt());
            existingBusDelay.setResolvedAt(busDelay.getResolvedAt());

            return busDelayRepository.save(existingBusDelay);
        }

        return null;
    }

    @Override
    public void deleteBusDelay(Long id) {
        busDelayRepository.deleteById(id);
    }

}