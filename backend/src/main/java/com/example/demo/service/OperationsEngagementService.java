package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Operations_Engagement;

public interface OperationsEngagementService {

    Operations_Engagement saveOperationsEngagement(Operations_Engagement operationsEngagement);

    List<Operations_Engagement> getAllOperationsEngagements();

    Optional<Operations_Engagement> getOperationsEngagementById(Long id);

    Operations_Engagement updateOperationsEngagement(Long id, Operations_Engagement operationsEngagement);

    void deleteOperationsEngagement(Long id);

}