package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.AppointmentNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;
import com.hsbc.wfs6.team2.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class AppointmentDaoImpl implements AppointmentDao{
    @Override
    public void addAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment (appointmentId, patientId, doctorId, appointmentDate, appointmentTime, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, appointment.getAppointmentId());
            preparedStatement.setInt(2, appointment.getPatientId());
            preparedStatement.setInt(3, appointment.getDoctorId());
            preparedStatement.setDate(4, Date.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setTime(5, Time.valueOf(appointment.getAppointmentTime()));
            preparedStatement.setString(6, appointment.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
        String query = "SELECT * FROM appointment WHERE appointmentId = ?";
        Appointment appointment = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, appointmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                appointment = new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate").toLocalDate(),
                        resultSet.getTime("appointmentTime").toLocalTime(),
                        resultSet.getString("status")
                );
            }
            else{
                throw new AppointmentNotFoundException("Appointment with ID "+appointmentId +"not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        String query = "SELECT * FROM appointment";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate").toLocalDate(),
                        resultSet.getTime("appointmentTime").toLocalTime(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        String query = "SELECT * FROM appointment WHERE doctorId = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate").toLocalDate(),
                        resultSet.getTime("appointmentTime").toLocalTime(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        String query = "SELECT * FROM appointment WHERE patientId = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getDate("appointmentDate").toLocalDate(),
                        resultSet.getTime("appointmentTime").toLocalTime(),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        String query = "UPDATE appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, appointmentTime = ?, status = ? WHERE appointmentId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

           // preparedStatement.setInt(1, appointment.getAppointmentId());
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setTime(4, Time.valueOf(appointment.getAppointmentTime()));
            preparedStatement.setString(5, appointment.getStatus());
            preparedStatement.setInt(6, appointment.getAppointmentId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {
        String query = "DELETE FROM appointment WHERE appointmentId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, appointmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAppointmentsForTheDay(int doctorId, LocalDate appointmentDate) {
        List<Appointment> result = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctorId = ? AND DATE(appointmentDate) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            stmt.setDate(2, Date.valueOf(appointmentDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getDate("appointmentDate").toLocalDate(),
                        rs.getTime("appointmentTime").toLocalTime(),
                        rs.getString("status")
                );
                result.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}

