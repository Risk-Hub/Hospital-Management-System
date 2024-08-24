package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.daolayers.DoctorDao;
import com.hsbc.wfs6.team2.daolayers.DoctorDaoImpl;
import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDao.addDoctor(doctor);
    }
    @Override
    public Doctor getDoctorById(int doctorId) throws DoctorNotFoundException {
        return doctorDao.getDoctorById(doctorId);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorDao.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(int doctorId) throws DoctorNotFoundException {
        doctorDao.deleteDoctor(doctorId);
    }

    @Override
    public void addSchedule(Schedule schedule) throws SQLException
    {
        doctorDao.addSchedule(schedule);
    }

    @Override
    public List<Schedule> getDoctorSchedules(int doctorId) {
        return doctorDao.getDoctorSchedules(doctorId);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String doctorSpecialization) {
        return doctorDao.getDoctorsBySpecialization(doctorSpecialization);
    }
}
