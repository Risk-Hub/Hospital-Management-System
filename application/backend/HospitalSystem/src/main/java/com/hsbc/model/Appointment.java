package com.hsbc.model;

import java.time.LocalDate;

public class Appointment {
    private int doctorId;
    private LocalDate appointmentDate;
    private String timeslot;
    private boolean isAvailable;
    private int patientId;

    public Appointment() {
    }

    public Appointment(int doctorId, LocalDate appointmentDate, String timeslot, boolean isAvailable, int patientIdIfBooked) {
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.timeslot = timeslot;
        this.isAvailable = isAvailable;
        this.patientId = patientIdIfBooked;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientIdIfBooked) {
        this.patientId = patientIdIfBooked;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", timeslot='" + timeslot + '\'' +
                ", isAvailable=" + isAvailable +
                ", patientId=" + patientId +
                '}';
    }
}
