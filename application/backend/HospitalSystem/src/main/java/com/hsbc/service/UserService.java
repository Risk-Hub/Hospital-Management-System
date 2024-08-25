package com.hsbc.service;

import com.hsbc.model.Appointment;
import com.hsbc.model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    boolean addNewPatient(Patient patient) throws SQLException, ClassNotFoundException;
    boolean bookAnAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;
    List<Appointment> allAppointments() throws SQLException, ClassNotFoundException;
}
