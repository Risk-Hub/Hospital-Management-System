package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.daolayers.AppointmentDao;
import com.hsbc.wfs6.team2.daolayers.AppointmentDaoImpl;
import com.hsbc.wfs6.team2.exceptions.AppointmentNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentDao appointmentDao = new AppointmentDaoImpl();

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentDao.addAppointment(appointment);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
        return appointmentDao.getAppointmentById(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentDao.getAppointmentsByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentDao.getAppointmentsByPatientId(patientId);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentDao.updateAppointment(appointment);
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {
       return appointmentDao.deleteAppointment(appointmentId);

    }

    @Override
    public List<Appointment> getAppointmentsForDay(int doctorId, LocalDate appointmentDate) {
        return appointmentDao.getAppointmentsForTheDay(doctorId, appointmentDate);
    }
}
