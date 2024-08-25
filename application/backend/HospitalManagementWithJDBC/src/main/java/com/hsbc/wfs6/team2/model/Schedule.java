package com.hsbc.wfs6.team2.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Schedule {

    private int doctorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    Schedule() {
    }

    // Constructor
    public Schedule(int doctorId, LocalDateTime startTime, LocalDateTime endTime) {

        this.doctorId = doctorId;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                ", doctorId=" + doctorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
