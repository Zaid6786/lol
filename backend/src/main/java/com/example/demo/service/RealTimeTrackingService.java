package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Real_Time_Tracking;

public interface RealTimeTrackingService {

    Real_Time_Tracking saveRealTimeTracking(Real_Time_Tracking realTimeTracking);

    List<Real_Time_Tracking> getAllRealTimeTrackings();

    Optional<Real_Time_Tracking> getRealTimeTrackingById(Long id);

    Real_Time_Tracking updateRealTimeTracking(Long id, Real_Time_Tracking realTimeTracking);

    void deleteRealTimeTracking(Long id);

}