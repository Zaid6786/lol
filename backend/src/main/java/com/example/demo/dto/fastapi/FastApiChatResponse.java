package com.example.demo.dto.fastapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

public class FastApiChatResponse {

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("intent")
    private String intent;

    @JsonProperty("assigned_bus_number")
    private String assignedBusNumber;

    @JsonProperty("current_location_name")
    private String currentLocationName;

    @JsonProperty("estimated_arrival_minutes")
    private Integer estimatedArrivalMinutes;

    @JsonProperty("estimated_arrival_time")
    private String estimatedArrivalTime;

    @JsonProperty("delay_minutes")
    private Integer delayMinutes;

    @JsonProperty("fee_due")
    private Double feeDue;

    @JsonProperty("fee_due_date")
    private LocalDate feeDueDate;

    @JsonProperty("data_available")
    private boolean dataAvailable;

    @JsonProperty("warnings")
    private List<String> warnings;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getAssignedBusNumber() {
        return assignedBusNumber;
    }

    public void setAssignedBusNumber(String assignedBusNumber) {
        this.assignedBusNumber = assignedBusNumber;
    }

    public String getCurrentLocationName() {
        return currentLocationName;
    }

    public void setCurrentLocationName(String currentLocationName) {
        this.currentLocationName = currentLocationName;
    }

    public Integer getEstimatedArrivalMinutes() {
        return estimatedArrivalMinutes;
    }

    public void setEstimatedArrivalMinutes(Integer estimatedArrivalMinutes) {
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public Double getFeeDue() {
        return feeDue;
    }

    public void setFeeDue(Double feeDue) {
        this.feeDue = feeDue;
    }

    public LocalDate getFeeDueDate() {
        return feeDueDate;
    }

    public void setFeeDueDate(LocalDate feeDueDate) {
        this.feeDueDate = feeDueDate;
    }

    public boolean isDataAvailable() {
        return dataAvailable;
    }

    public void setDataAvailable(boolean dataAvailable) {
        this.dataAvailable = dataAvailable;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
