package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface DoctorService {

    public void addDoctor(Doctor doctor);
    public Doctor getDoctorById(int doctorId) throws DoctorNotFoundException;

    public List<Doctor> getAllDoctors();

    public void updateDoctor(Doctor doctor);

    public void deleteDoctor(int doctorId) throws DoctorNotFoundException;

    public void addSchedule(Schedule schedule) throws SQLException;

    public List<Schedule> getDoctorSchedules(int doctorId);

    public List<Doctor> getDoctorsBySpecialization(String doctorSpecialization);
}
