package com.hsbc.service;

import com.hsbc.dao.AdminDao;
import com.hsbc.dao.AdminDaoImpl;
import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AdminServiceImpl implements AdminService{
    AdminDao adminDao=new AdminDaoImpl();

    @Override
    public boolean generateAppointmentsByDoctorReport(int doctorId) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.generateAppointmentsByDoctorReport(doctorId);
    }

    @Override
    public boolean generateAppointmentsForDayReport(LocalDate date) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.generateAppointmentsForDayReport(date);
    }

    @Override
    public boolean generatePatientVisitsReport(int patientId) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.generatePatientVisitsReport(patientId);
    }

    @Override
    public boolean generatePatientsInAgeGroupReport(int minAge, int maxAge) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.generatePatientsInAgeGroupReport(minAge,maxAge);
    }

    @Override
    public boolean generatePatientsByGenderReport(String gender) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.generatePatientsByGenderReport(gender);
    }

    @Override
    public List<Doctor> allDoctors() throws SQLException, ClassNotFoundException {
        return adminDao.allDoctors();
    }

    @Override
    public List<Appointment> scheduleOfDoctor(int doctorId) throws SQLException, ClassNotFoundException {
        return adminDao.scheduleOfDoctor(doctorId);
    }

    @Override
    public boolean cancelAppointmentsForCurrentDay(int doctorId) throws SQLException, ClassNotFoundException {
        return adminDao.cancelAppointmentsForCurrentDay(doctorId);
    }

    @Override
    public List<Patient> listAllPatients() throws SQLException, ClassNotFoundException {
        return adminDao.listAllPatients();
    }

    @Override
    public List<Patient> patientsFilteredByGender(String gender) throws SQLException, ClassNotFoundException {
        return adminDao.patientsFilteredByGender(gender);
    }

    @Override
    public List<Patient> patientsInAnAgeGroup(int age1, int age2) throws SQLException, ClassNotFoundException {
        return adminDao.patientsInAnAgeGroup(age1,age2);
    }

    @Override
    public boolean importDoctor(String filepath) throws SQLException, ClassNotFoundException, IOException {
        return adminDao.importDoctor(filepath);
    }
}
