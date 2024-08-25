package com.hsbc.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {


    public boolean writeToJson(String fileName, Object data) throws IOException {
        Gson gson = GsonConfig.createGson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
            return true;
        }
    }

    @Override
    public boolean generateAppointmentsByDoctorReport(int doctorId) throws SQLException, ClassNotFoundException, IOException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("select * from appointments where doctorId=?");
            preparedStatement.setInt(1,doctorId);
            ResultSet rs=preparedStatement.executeQuery();
            List<Appointment> appointmentsByDoctor=new ArrayList<>();
            while(rs.next()){
                appointmentsByDoctor.add(new Appointment(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getString(3),rs.getBoolean(4),rs.getInt(5)));
            }
            preparedStatement.close();
            rs.close();
            return writeToJson("appointments_by_doctor_"+doctorId+".json",appointmentsByDoctor);
        }
    }

    @Override
    public boolean generateAppointmentsForDayReport(LocalDate date) throws SQLException, ClassNotFoundException, IOException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM appointments WHERE appointmentDate = ?");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet rs= preparedStatement.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("doctorId"),
                        rs.getDate("appointmentDate").toLocalDate(),
                        rs.getString("timeslot"),
                        rs.getBoolean("isAvailable"),
                        rs.getInt("patientId")));
            }
            return writeToJson("appointments_for_day_" + date + ".json", appointments);
        }
    }

    @Override
    public boolean generatePatientVisitsReport(int patientId) throws SQLException, ClassNotFoundException, IOException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(*) AS visitCount FROM appointments WHERE patientId = ?");
            preparedStatement.setInt(1,patientId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int visitCount = rs.getInt("visitCount");
                return writeToJson("patient_visits_" + patientId + ".json", visitCount);
            }
        }
        return false;
    }

    @Override
    public boolean generatePatientsInAgeGroupReport(int minAge, int maxAge) throws SQLException, ClassNotFoundException, IOException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(*) AS patientCount FROM patientDetails WHERE patientAge BETWEEN ? AND ?");
            preparedStatement.setInt(1,minAge);
            preparedStatement.setInt(2,maxAge);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int patientCount = rs.getInt("patientCount");
                return writeToJson("patients_in_age_group_" + minAge + "_" + maxAge + ".json", patientCount);
            }
        }
        return false;
    }

    @Override
    public boolean generatePatientsByGenderReport(String gender) throws SQLException, ClassNotFoundException, IOException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT COUNT(*) AS patientCount FROM patientDetails WHERE patientGender =");
            preparedStatement.setString(1,gender);
            ResultSet rs=preparedStatement.executeQuery();
            if (rs.next()) {
                int patientCount = rs.getInt("patientCount");
                 return writeToJson("patients_by_gender_" + gender + ".json", patientCount);
            }
        }
        return false;
    }

    @Override
    public List<Doctor> allDoctors() throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from doctor");
            ResultSet rs = preparedStatement.executeQuery();
            List<Doctor> doctorList = new ArrayList<>();
            Doctor doctor = new Doctor();
            while (rs.next()) {
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setDoctorContactNo(rs.getLong("doctorContactNo"));
                doctor.setDoctorEMail(rs.getString("doctorEmail"));
                doctorList.add(doctor);
            }
            rs.close();
            preparedStatement.close();
            return doctorList;
        }
    }

    @Override
    public List<Appointment> scheduleOfDoctor(int doctorId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            LocalDate currentDay = LocalDate.now();
            LocalDate threeDaysFromCurrentDay = currentDay.plusDays(3);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointments WHERE doctorId=? AND appointmentDate BETWEEN ? AND ? ");
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setDate(2, Date.valueOf(currentDay));
            preparedStatement.setDate(3, Date.valueOf(threeDaysFromCurrentDay));

            ResultSet rs = preparedStatement.executeQuery();
            List<Appointment> appointmentsOfDoctor = new ArrayList<>();
            while (rs.next()) {
                appointmentsOfDoctor.add(new Appointment(doctorId, rs.getDate("appointmentDate").toLocalDate(), rs.getString("timeslot"), rs.getBoolean("isAvailable"), rs.getInt("patientId")));
            }
            rs.close();
            preparedStatement.close();
            return appointmentsOfDoctor;
        }
    }

    @Override
    public boolean cancelAppointmentsForCurrentDay(int doctorId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from appointments where doctorId=? and appointmentDate=?");
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            boolean result = preparedStatement.execute();
            preparedStatement.close();
            return result;
        }
    }

    @Override
    public List<Patient> listAllPatients() throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from patientDetails");
            ResultSet rs = preparedStatement.executeQuery();
            List<Patient> allPatients = new ArrayList<>();
            while (rs.next()) {
                allPatients.add(new Patient(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
            rs.close();
            preparedStatement.close();
            return allPatients;
        }
    }

    @Override
    public List<Patient> patientsFilteredByGender(String gender) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from patientDetails where patientGender like ?");
            preparedStatement.setString(1, gender);
            ResultSet rs = preparedStatement.executeQuery();
            List<Patient> patientList = new ArrayList<>();
            while (rs.next()) {
                patientList.add(new Patient(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
            rs.close();
            preparedStatement.close();
            return patientList;
        }
    }

    @Override
    public List<Patient> patientsInAnAgeGroup(int age1, int age2) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from patientDetails where patientAge>=? and patientAge<=?");
            preparedStatement.setInt(1, age1);
            preparedStatement.setInt(2, age2);
            ResultSet rs = preparedStatement.executeQuery();
            List<Patient> patientList = new ArrayList<>();
            while (rs.next()) {
                patientList.add(new Patient(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
            rs.close();
            preparedStatement.close();
            return patientList;
        }
    }

    @Override
    public boolean importDoctor(String filePath) throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            Gson gson =GsonConfig.createGson();
            FileReader reader = new FileReader(filePath);
            Doctor doctor = gson.fromJson(reader, Doctor.class);
            reader.close();

            PreparedStatement checkStatement = connection.prepareStatement("select count(*) from doctor where doctorId=? and loginId=?");
            PreparedStatement preparedStatementForCredentials = connection.prepareStatement("insert into staffLoginCredentials values (?,?,?)");
            PreparedStatement preparedStatementForDoctor = connection.prepareStatement("INSERT INTO doctor (doctorId, doctorName, doctorContactNo, doctorEmail, loginId) VALUES (?, ?, ?, ?, ?)");

            checkStatement.setInt(1, doctor.getDoctorId());
            checkStatement.setString(2, doctor.getLoginId());
            ResultSet rs = checkStatement.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                preparedStatementForCredentials.setString(1, doctor.getLoginId());
                preparedStatementForCredentials.setString(2, doctor.getPassword());
                preparedStatementForCredentials.setString(3, doctor.getStaffType());
                int rowInserted = preparedStatementForCredentials.executeUpdate();
                if (rowInserted == 1) {
                    preparedStatementForDoctor.setInt(1, doctor.getDoctorId());
                    preparedStatementForDoctor.setString(2, doctor.getDoctorName());
                    preparedStatementForDoctor.setLong(3, doctor.getDoctorContactNo());
                    preparedStatementForDoctor.setString(4, doctor.getDoctorEMail());
                    preparedStatementForDoctor.setString(5, doctor.getLoginId());
                    int result = preparedStatementForDoctor.executeUpdate();
                    if (result == 1)
                        return true;
                }
            }
        }
        return false;
    }
}
