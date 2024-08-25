package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Schedule;
import com.hsbc.wfs6.team2.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DoctorDaoImpl implements DoctorDao{
    @Override
    public void addDoctor(Doctor doctor) {
        //adds new doctor in database
        String query = "INSERT INTO doctor (doctorId, doctorName, doctorSpecialization, doctorEmail, doctorContactNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctor.getDoctorId());
            preparedStatement.setString(2, doctor.getDoctorName());
            preparedStatement.setString(3, doctor.getDoctorSpecialization());
            preparedStatement.setString(4, doctor.getDoctorEmail());
            preparedStatement.setString(5, doctor.getDoctorContactNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws DoctorNotFoundException {
        String query = "SELECT * FROM doctor WHERE doctorId = ?";
        Doctor doctor = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                doctor = new Doctor(
                        resultSet.getInt("doctorId"),
                        resultSet.getString("doctorName"),
                        resultSet.getString("doctorSpecialization"),
                        resultSet.getString("doctorEmail"),
                        resultSet.getString("doctorContactNumber")
                );
            }
            else {
                throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        String query = "SELECT * FROM doctor";
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                doctors.add(new Doctor(
                        resultSet.getInt("doctorId"),
                        resultSet.getString("doctorName"),
                        resultSet.getString("doctorSpecialization"),
                        resultSet.getString("doctorEmail"),
                        resultSet.getString("doctorContactNumber")


                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET doctorName = ?, doctorSpecialization = ? , doctorEmail = ?, doctorContactNumber = ? WHERE doctorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, doctor.getDoctorName());
            preparedStatement.setString(2, doctor.getDoctorSpecialization());
            preparedStatement.setString(3, doctor.getDoctorEmail());
            preparedStatement.setString(4, doctor.getDoctorContactNumber());
            preparedStatement.setInt(5, doctor.getDoctorId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int doctorId) throws DoctorNotFoundException {
        String query = "DELETE FROM doctor WHERE doctorId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSchedule(Schedule schedule) throws SQLException
    {
        String query = "INSERT INTO schedule (doctorId, startTime, endTime) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, schedule.getDoctorId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(schedule.getStartTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(schedule.getEndTime()));

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Schedule> getDoctorSchedules(int doctorId) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE doctorId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("doctorId"),
                        rs.getTimestamp("startTime").toLocalDateTime(),
                        rs.getTimestamp("endTime").toLocalDateTime()
                );
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor WHERE doctorSpecialization LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the parameter for the specialization, with '%' wildcards for partial match
            pstmt.setString(1, "%" + specialization + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Create a Doctor object for each row in the ResultSet
                    Doctor doctor = new Doctor(
                            rs.getInt("doctorId"),
                            rs.getString("doctorName"),
                            rs.getString("doctorSpecialization"),
                            rs.getString("doctorEmail"),
                            rs.getString("doctorContactNumber")
                    );
                    // Add the Doctor to the list
                    doctors.add(doctor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }


}
