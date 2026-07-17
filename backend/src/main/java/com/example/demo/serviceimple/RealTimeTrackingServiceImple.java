package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Real_Time_Tracking;
import com.example.demo.repository.RealTimeTrackingRepository;
import com.example.demo.service.RealTimeTrackingService;

@Service
public class RealTimeTrackingServiceImple implements RealTimeTrackingService {

    @Autowired
    private RealTimeTrackingRepository realTimeTrackingRepository;

    @Override
    public Real_Time_Tracking saveRealTimeTracking(Real_Time_Tracking realTimeTracking) {
        return realTimeTrackingRepository.save(realTimeTracking);
    }

    @Override
    public List<Real_Time_Tracking> getAllRealTimeTrackings() {
        return realTimeTrackingRepository.findAll();
    }

    @Override
    public Optional<Real_Time_Tracking> getRealTimeTrackingById(Long id) {
        return realTimeTrackingRepository.findById(id);
    }

    @Override
    public Real_Time_Tracking updateRealTimeTracking(Long id,
            Real_Time_Tracking realTimeTracking) {

        Real_Time_Tracking existingRealTimeTracking =
                realTimeTrackingRepository.findById(id).orElse(null);

        if (existingRealTimeTracking != null) {

            existingRealTimeTracking.setBusId(realTimeTracking.getBusId());
            existingRealTimeTracking.setLatitude(realTimeTracking.getLatitude());
            existingRealTimeTracking.setLongitude(realTimeTracking.getLongitude());
            existingRealTimeTracking.setCurrentSpeed(realTimeTracking.getCurrentSpeed());
            existingRealTimeTracking.setCurrentStop(realTimeTracking.getCurrentStop());
            existingRealTimeTracking.setNextStop(realTimeTracking.getNextStop());
            existingRealTimeTracking.setEtaMinutes(realTimeTracking.getEtaMinutes());
            existingRealTimeTracking.setTrackingTime(realTimeTracking.getTrackingTime());

            return realTimeTrackingRepository.save(existingRealTimeTracking);
        }

        return null;
    }

    @Override
    public void deleteRealTimeTracking(Long id) {
        realTimeTrackingRepository.deleteById(id);
    }

}