package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao {

    void addDoctor(Doctor doctor);
    Doctor getDoctorById(int doctorId) throws DoctorNotFoundException;
    List<Doctor> getAllDoctors();
    void updateDoctor(Doctor doctor);
    void deleteDoctor(int doctorId) throws DoctorNotFoundException;
    void addSchedule(Schedule schedule) throws SQLException;
    List<Schedule> getDoctorSchedules(int doctorId);
    List<Doctor> getDoctorsBySpecialization(String specialization);
}
