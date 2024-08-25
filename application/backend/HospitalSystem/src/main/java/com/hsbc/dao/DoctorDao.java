package com.hsbc.dao;

import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao {
    boolean addSchedule(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException;

    boolean cancelAppointment(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException;

    List<Appointment> viewAppointments(Doctor doctor) throws SQLException, ClassNotFoundException;

    List<String> suggestMedicalTests(Patient patient) throws SQLException, ClassNotFoundException;

    List<String> suggestMedicines(Patient patient) throws SQLException, ClassNotFoundException;
}
