package com.hsbc.dao;

import com.hsbc.model.Appointment;
import com.hsbc.model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public boolean createPatient(Patient patient) throws SQLException, ClassNotFoundException;
    public boolean bookAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;
    public List<Appointment> allAppointments() throws SQLException, ClassNotFoundException;
}
