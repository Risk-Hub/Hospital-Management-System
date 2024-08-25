package com.hsbc.dao;

import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AdminDao {
    boolean generateAppointmentsByDoctorReport(int doctorId) throws SQLException, ClassNotFoundException, IOException;
    boolean generateAppointmentsForDayReport(LocalDate date) throws SQLException, ClassNotFoundException, IOException;
    boolean generatePatientVisitsReport(int patientId) throws SQLException, ClassNotFoundException, IOException;
    boolean generatePatientsInAgeGroupReport(int minAge,int maxAge) throws SQLException, ClassNotFoundException, IOException;
    boolean generatePatientsByGenderReport(String gender) throws SQLException, ClassNotFoundException, IOException;
    List<Doctor> allDoctors() throws SQLException, ClassNotFoundException;
    List<Appointment> scheduleOfDoctor(int doctorId) throws SQLException, ClassNotFoundException;
    boolean cancelAppointmentsForCurrentDay(int doctorId) throws SQLException, ClassNotFoundException;
    List<Patient> listAllPatients() throws SQLException, ClassNotFoundException;
    List<Patient> patientsFilteredByGender(String gender) throws SQLException, ClassNotFoundException;
    List<Patient> patientsInAnAgeGroup(int age1,int age2) throws SQLException, ClassNotFoundException;
    boolean importDoctor(String filepath) throws SQLException, ClassNotFoundException, IOException;//json/xml
}
