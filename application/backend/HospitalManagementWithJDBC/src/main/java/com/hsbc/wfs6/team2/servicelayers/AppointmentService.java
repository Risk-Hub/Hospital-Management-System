package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.exceptions.AppointmentNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    public void addAppointment(Appointment appointment);
    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException;
    public List<Appointment> getAllAppointments();
    public List<Appointment> getAppointmentsByDoctorId(int doctorId);
    public List<Appointment> getAppointmentsByPatientId(int patientId);
    public void updateAppointment(Appointment appointment);
    public boolean deleteAppointment(int appointmentId);
    public List<Appointment> getAppointmentsForDay(int doctorId, LocalDate appointmentDate);

    }
