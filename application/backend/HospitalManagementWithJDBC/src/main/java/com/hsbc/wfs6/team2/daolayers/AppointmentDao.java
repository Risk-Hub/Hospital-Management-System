package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.AppointmentNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentDao {
    void addAppointment(Appointment appointment);
    Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException;
    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentsByDoctorId(int doctorId);
    List<Appointment> getAppointmentsByPatientId(int patientId);
    void updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);
    List<Appointment> getAppointmentsForTheDay(int doctorId, LocalDate date);
}
