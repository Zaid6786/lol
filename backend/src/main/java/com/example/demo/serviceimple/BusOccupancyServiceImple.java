package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Bus_Occupancy;
import com.example.demo.repository.BusOccupancyRepository;
import com.example.demo.service.BusOccupancyService;

@Service
public class BusOccupancyServiceImple implements BusOccupancyService {

    @Autowired
    private BusOccupancyRepository busOccupancyRepository;


    @Override
    public Bus_Occupancy saveBusOccupancy(Bus_Occupancy busOccupancy) {
        return busOccupancyRepository.save(busOccupancy);
    }


    @Override
    public List<Bus_Occupancy> getAllBusOccupancies() {
        return busOccupancyRepository.findAll();
    }


    @Override
    public Optional<Bus_Occupancy> getBusOccupancyById(Long id) {
        return busOccupancyRepository.findById(id);
    }


    @Override
    public Bus_Occupancy updateBusOccupancy(Long id, Bus_Occupancy busOccupancy) {

        Bus_Occupancy existingBusOccupancy = 
                busOccupancyRepository.findById(id).orElse(null);

        if (existingBusOccupancy != null) {

            existingBusOccupancy.setBusId(busOccupancy.getBusId());
            existingBusOccupancy.setOccupied(busOccupancy.getOccupied());
            existingBusOccupancy.setAvailable(busOccupancy.getAvailable());
            existingBusOccupancy.setOccupancyPercentage(
                    busOccupancy.getOccupancyPercentage()
            );
            existingBusOccupancy.setCrowdLevel(
                    busOccupancy.getCrowdLevel()
            );
            existingBusOccupancy.setUpdatedTime(
                    busOccupancy.getUpdatedTime()
            );

            return busOccupancyRepository.save(existingBusOccupancy);
        }

        return null;
    }


    @Override
    public void deleteBusOccupancy(Long id) {
        busOccupancyRepository.deleteById(id);
    }

}