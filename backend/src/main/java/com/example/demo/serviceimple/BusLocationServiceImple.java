package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Bus_Location;
import com.example.demo.repository.BusLocationRepository;
import com.example.demo.service.BusLocationService;

@Service
public class BusLocationServiceImple implements BusLocationService {

    @Autowired
    private BusLocationRepository busLocationRepository;

    @Override
    public Bus_Location saveBusLocation(Bus_Location busLocation) {
        return busLocationRepository.save(busLocation);
    }

    @Override
    public List<Bus_Location> getAllBusLocations() {
        return busLocationRepository.findAll();
    }

    @Override
    public Optional<Bus_Location> getBusLocationById(Long id) {
        return busLocationRepository.findById(id);
    }

    @Override
    public Bus_Location updateBusLocation(Long id, Bus_Location busLocation) {

        Bus_Location existingBusLocation = busLocationRepository.findById(id).orElse(null);

        if (existingBusLocation != null) {

            existingBusLocation.setBusId(busLocation.getBusId());
            existingBusLocation.setLatitude(busLocation.getLatitude());
            existingBusLocation.setLongitude(busLocation.getLongitude());
            existingBusLocation.setSpeed(busLocation.getSpeed());
            existingBusLocation.setHeading(busLocation.getHeading());
            existingBusLocation.setAccuracy(busLocation.getAccuracy());
            existingBusLocation.setTimestamp(busLocation.getTimestamp());

            return busLocationRepository.save(existingBusLocation);
        }

        return null;
    }

    @Override
    public void deleteBusLocation(Long id) {
        busLocationRepository.deleteById(id);
    }

}