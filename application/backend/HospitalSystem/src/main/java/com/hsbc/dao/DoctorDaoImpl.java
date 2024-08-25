package com.hsbc.dao;

import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao{
    @Override
    public boolean addSchedule(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException {
        try(Connection connection= DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("insert into appointments values (?,?,?,?,?)");
            LocalDate today=LocalDate.now();
            LocalDate threeDaysFromToday=today.plusDays(3);
            LocalDate dateOfAppointment=appointment.getAppointmentDate();
            boolean isInRange=(dateOfAppointment.isEqual(today)||dateOfAppointment.isAfter(today) && dateOfAppointment.isBefore(threeDaysFromToday)||dateOfAppointment.isEqual(threeDaysFromToday));
            if(isInRange){
                preparedStatement.setInt(1,doctor.getDoctorId());
                preparedStatement.setDate(2,Date.valueOf(appointment.getAppointmentDate()));
                preparedStatement.setString(3,appointment.getTimeslot());
                preparedStatement.setBoolean(4,appointment.isAvailable());
                preparedStatement.setInt(5,appointment.getPatientId());
                preparedStatement.close();
                return preparedStatement.execute();
            }
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(Appointment appointment, Doctor doctor) throws SQLException, ClassNotFoundException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("delete from appointments where doctorId=? and appointmentDate=? and timeslot=?");
            preparedStatement.setInt(1,doctor.getDoctorId());
            preparedStatement.setDate(2,Date.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setString(3,appointment.getTimeslot());
            boolean result=preparedStatement.execute();
            preparedStatement.close();
            return result;
        }
    }

    @Override
    public List<Appointment> viewAppointments(Doctor doctor) throws SQLException, ClassNotFoundException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            LocalDate currentDay=LocalDate.now();
            LocalDate threeDaysFromCurrentDay=currentDay.plusDays(3);
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM appointments WHERE doctorId=? AND appointmentDate BETWEEN ? AND ? ");
            preparedStatement.setInt(1,doctor.getDoctorId());
            preparedStatement.setDate(2, Date.valueOf(currentDay));
            preparedStatement.setDate(3,Date.valueOf(threeDaysFromCurrentDay));

            ResultSet rs= preparedStatement.executeQuery();
            List<Appointment> appointmentsOfDoctor=new ArrayList<>();
            while (rs.next()){
                appointmentsOfDoctor.add(new Appointment(doctor.getDoctorId(),rs.getDate("appointmentDate").toLocalDate(),rs.getString("timeslot"),rs.getBoolean("isAvailable"),rs.getInt("patientId")));
            }
            rs.close();
            preparedStatement.close();
            return appointmentsOfDoctor;
        }
    }

    @Override
    public List<String> suggestMedicalTests(Patient patient) throws SQLException, ClassNotFoundException {
       try(Connection connection= DatabaseConnect.initializeDb()){
           PreparedStatement preparedStatement=connection.prepareStatement("select * from medicinesNeeded where diseaseName like ?");
           preparedStatement.setString(1,patient.getDiseaseName());
           ResultSet rs=preparedStatement.executeQuery();
           List<String> suggestedTests=new ArrayList<>();
           while (rs.next()){
               suggestedTests.add(rs.getString("medicineName"));
           }
           rs.close();
           preparedStatement.close();
           return suggestedTests;
       }
    }

    @Override
    public List<String> suggestMedicines(Patient patient) throws SQLException, ClassNotFoundException {
        try(Connection connection=DatabaseConnect.initializeDb()){
            PreparedStatement preparedStatement=connection.prepareStatement("Select * from  testsNeeded where diseaseName like ?");
            preparedStatement.setString(1,patient.getDiseaseName());
            ResultSet rs=preparedStatement.executeQuery();
            List<String> medicinesForThePatient=new ArrayList<>();
            while (rs.next()){
                medicinesForThePatient.add(rs.getString("testsName"));
            }
            rs.close();
            preparedStatement.close();
            return medicinesForThePatient;
        }

    }
}
