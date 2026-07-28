package com.example.demo.dto.fastapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class FastApiChatRequest {

    private String sessionId;
    private StudentContext student;
    private String question;
    private AssignedBus assignedBus;
    private PickupStop pickupStop;
    private LiveTracking liveTracking;
    private DelayInformation delay;
    private FeeInformation fee;

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("student")
    public StudentContext getStudent() {
        return student;
    }

    public void setStudent(StudentContext student) {
        this.student = student;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("assigned_bus")
    public AssignedBus getAssignedBus() {
        return assignedBus;
    }

    public void setAssignedBus(AssignedBus assignedBus) {
        this.assignedBus = assignedBus;
    }

    @JsonProperty("pickup_stop")
    public PickupStop getPickupStop() {
        return pickupStop;
    }

    public void setPickupStop(PickupStop pickupStop) {
        this.pickupStop = pickupStop;
    }

    @JsonProperty("live_tracking")
    public LiveTracking getLiveTracking() {
        return liveTracking;
    }

    public void setLiveTracking(LiveTracking liveTracking) {
        this.liveTracking = liveTracking;
    }

    @JsonProperty("delay")
    public DelayInformation getDelay() {
        return delay;
    }

    public void setDelay(DelayInformation delay) {
        this.delay = delay;
    }

    @JsonProperty("fee")
    public FeeInformation getFee() {
        return fee;
    }

    public void setFee(FeeInformation fee) {
        this.fee = fee;
    }

    public static class StudentContext {
        private Long studentId;
        private String studentName;
        private String registrationNumber;

        @JsonProperty("student_id")
        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        @JsonProperty("student_name")
        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        @JsonProperty("registration_number")
        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }
    }

    public static class AssignedBus {
        private Long busId;
        private String busNumber;
        private String registrationNumber;
        private String routeName;
        private String driverName;
        private String driverPhoneMasked;
        private String status;

        @JsonProperty("bus_id")
        public Long getBusId() {
            return busId;
        }

        public void setBusId(Long busId) {
            this.busId = busId;
        }

        @JsonProperty("bus_number")
        public String getBusNumber() {
            return busNumber;
        }

        public void setBusNumber(String busNumber) {
            this.busNumber = busNumber;
        }

        @JsonProperty("registration_number")
        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        @JsonProperty("route_name")
        public String getRouteName() {
            return routeName;
        }

        public void setRouteName(String routeName) {
            this.routeName = routeName;
        }

        @JsonProperty("driver_name")
        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        @JsonProperty("driver_phone_masked")
        public String getDriverPhoneMasked() {
            return driverPhoneMasked;
        }

        public void setDriverPhoneMasked(String driverPhoneMasked) {
            this.driverPhoneMasked = driverPhoneMasked;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class PickupStop {
        private Long stopId;
        private String stopName;
        private Double latitude;
        private Double longitude;
        private String scheduledTime;

        @JsonProperty("stop_id")
        public Long getStopId() {
            return stopId;
        }

        public void setStopId(Long stopId) {
            this.stopId = stopId;
        }

        @JsonProperty("stop_name")
        public String getStopName() {
            return stopName;
        }

        public void setStopName(String stopName) {
            this.stopName = stopName;
        }

        @JsonProperty("latitude")
        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        @JsonProperty("longitude")
        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        @JsonProperty("scheduled_time")
        public String getScheduledTime() {
            return scheduledTime;
        }

        public void setScheduledTime(String scheduledTime) {
            this.scheduledTime = scheduledTime;
        }
    }

    public static class LiveTracking {
        private String currentLocationName;
        private Double latitude;
        private Double longitude;
        private Double distanceToStopKm;
        private Integer estimatedArrivalMinutes;
        private String estimatedArrivalTime;
        private String lastUpdatedAt;

        @JsonProperty("current_location_name")
        public String getCurrentLocationName() {
            return currentLocationName;
        }

        public void setCurrentLocationName(String currentLocationName) {
            this.currentLocationName = currentLocationName;
        }

        @JsonProperty("latitude")
        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        @JsonProperty("longitude")
        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        @JsonProperty("distance_to_stop_km")
        public Double getDistanceToStopKm() {
            return distanceToStopKm;
        }

        public void setDistanceToStopKm(Double distanceToStopKm) {
            this.distanceToStopKm = distanceToStopKm;
        }

        @JsonProperty("estimated_arrival_minutes")
        public Integer getEstimatedArrivalMinutes() {
            return estimatedArrivalMinutes;
        }

        public void setEstimatedArrivalMinutes(Integer estimatedArrivalMinutes) {
            this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        }

        @JsonProperty("estimated_arrival_time")
        public String getEstimatedArrivalTime() {
            return estimatedArrivalTime;
        }

        public void setEstimatedArrivalTime(String estimatedArrivalTime) {
            this.estimatedArrivalTime = estimatedArrivalTime;
        }

        @JsonProperty("last_updated_at")
        public String getLastUpdatedAt() {
            return lastUpdatedAt;
        }

        public void setLastUpdatedAt(String lastUpdatedAt) {
            this.lastUpdatedAt = lastUpdatedAt;
        }
    }

    public static class DelayInformation {
        private boolean isDelayed;
        private int delayMinutes;
        private String reasonCode;
        private String reasonDescription;

        @JsonProperty("is_delayed")
        public boolean isDelayed() {
            return isDelayed;
        }

        public void setDelayed(boolean isDelayed) {
            this.isDelayed = isDelayed;
        }

        @JsonProperty("delay_minutes")
        public int getDelayMinutes() {
            return delayMinutes;
        }

        public void setDelayMinutes(int delayMinutes) {
            this.delayMinutes = delayMinutes;
        }

        @JsonProperty("reason_code")
        public String getReasonCode() {
            return reasonCode;
        }

        public void setReasonCode(String reasonCode) {
            this.reasonCode = reasonCode;
        }

        @JsonProperty("reason_description")
        public String getReasonDescription() {
            return reasonDescription;
        }

        public void setReasonDescription(String reasonDescription) {
            this.reasonDescription = reasonDescription;
        }
    }

    public static class FeeInformation {
        private Double totalFee;
        private Double amountPaid;
        private Double amountDue;
        private LocalDate dueDate;
        private String status;

        @JsonProperty("total_fee")
        public Double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(Double totalFee) {
            this.totalFee = totalFee;
        }

        @JsonProperty("amount_paid")
        public Double getAmountPaid() {
            return amountPaid;
        }

        public void setAmountPaid(Double amountPaid) {
            this.amountPaid = amountPaid;
        }

        @JsonProperty("amount_due")
        public Double getAmountDue() {
            return amountDue;
        }

        public void setAmountDue(Double amountDue) {
            this.amountDue = amountDue;
        }

        @JsonProperty("due_date")
        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
