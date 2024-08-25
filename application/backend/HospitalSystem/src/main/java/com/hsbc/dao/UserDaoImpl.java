package com.hsbc.dao;

import com.hsbc.model.Appointment;
import com.hsbc.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public boolean createPatient(Patient patient) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into patientDetails (patientId, patientName, patientContactNo, patientAge, patientGender, diseaseName, doctorId) values (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, patient.getPatientId());
            preparedStatement.setString(2, patient.getPatientName());
            preparedStatement.setLong(3, patient.getPatientContactNo());
            preparedStatement.setInt(4, patient.getPatientAge());
            preparedStatement.setString(5, patient.getPatientGender());
            preparedStatement.setString(6, patient.getDiseaseName());
            preparedStatement.setInt(7, patient.getDoctorId());
            return preparedStatement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean bookAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            for (Appointment appt : allAppointments()) {
                if (appt.getDoctorId() == appointment.getDoctorId() &&
                        appt.getAppointmentDate() == appointment.getAppointmentDate() &&
                appt.getTimeslot()== appointment.getTimeslot() && !appt.isAvailable()){
                    return false;
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement("update appointments set isAvailable=false,patientId=? where doctorId=? and appointmentDate=? and timeslot=? and isAvailable=true");
            //preparedStatement.setBoolean(1,false);
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setString(4, appointment.getTimeslot());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        }
    }

    @Override
    public List<Appointment> allAppointments() throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from appointments");
            ResultSet rs = preparedStatement.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                int doctorId = rs.getInt("doctorId");
                LocalDate date = rs.getDate("appointmentDate").toLocalDate();
                String timeslot = rs.getString("timeslot");
                boolean isAvailable = rs.getBoolean("isAvailable");
                int patientId = rs.getInt("patientId");
                appointments.add(new Appointment(doctorId, date, timeslot, isAvailable, patientId));
            }
            preparedStatement.close();
            return appointments;
        }
    }
}
