package com.hsbc.service;

import com.hsbc.dao.DoctorDao;
import com.hsbc.dao.DoctorDaoImpl;
import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    private DoctorDao doctorDao=new DoctorDaoImpl();

    @Override
    public boolean addSchedule(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException {
        return doctorDao.addSchedule(appointment,doctor);
    }

    @Override
    public boolean cancelAppointment(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException {
        return doctorDao.cancelAppointment(appointment, doctor);
    }

    @Override
    public List<Appointment> getAllAppointment(Doctor doctor) throws SQLException, ClassNotFoundException {
        return doctorDao.viewAppointments(doctor);
    }

    @Override
    public List<String> suggestMedicalTests(Patient patient) throws SQLException, ClassNotFoundException {
        return doctorDao.suggestMedicalTests(patient);
    }

    @Override
    public List<String> suggestMedicines(Patient patient) throws SQLException, ClassNotFoundException {
        return doctorDao.suggestMedicines(patient);
    }
}
