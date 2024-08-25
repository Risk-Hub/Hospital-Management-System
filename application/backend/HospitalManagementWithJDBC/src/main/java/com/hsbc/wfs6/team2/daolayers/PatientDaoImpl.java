package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.exceptions.PatientNotFoundException;
import com.hsbc.wfs6.team2.model.Patient;
import com.hsbc.wfs6.team2.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class PatientDaoImpl implements PatientDao{
    @Override
    public void addPatient(Patient patient) {  //add new patient record to database
        String query = "INSERT INTO patient (patientId, patientName, patientGender, patientAge, medicalProblem, contactDetails) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Sets the values for the SQL query using the patient object.

            preparedStatement.setInt(1, patient.getPatientId());
            preparedStatement.setString(2, patient.getPatientName());
            preparedStatement.setString(3, patient.getPatientGender());
            preparedStatement.setInt(4, patient.getPatientAge());
            preparedStatement.setString(5, patient.getmedicalProblem());
            preparedStatement.setString(6, patient.getContactDetails());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves a patient record from the database by patient ID.
    @Override
    public Patient getPatientById(int patientId) throws PatientNotFoundException {
        String query = "SELECT * FROM patient WHERE patientId = ?";
        Patient patient = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String medicalTestsStr = resultSet.getString("medicalTests");
                List<String> medicalTests = medicalTestsStr != null ? Arrays.asList(medicalTestsStr.split(",")) : new ArrayList<>();

                String medicinesStr = resultSet.getString("medicines");
                List<String> medicines = medicinesStr != null ? Arrays.asList(medicinesStr.split(",")) : new ArrayList<>();

                patient = new Patient(
                        resultSet.getInt("patientId"),
                        resultSet.getString("patientName"),

                        resultSet.getString("patientGender"),
                        resultSet.getInt("patientAge"),
                        resultSet.getString("medicalProblem"),
                        resultSet.getString("contactDetails"),
                        medicalTests,
                        medicines

                );
            }
            else {
                throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {

        // Retrieves all patient records from the database.
        String query = "SELECT * FROM patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String medicalTestsStr = resultSet.getString("medicalTests");
                List<String> medicalTests = medicalTestsStr != null ? Arrays.asList(medicalTestsStr.split(",")) : new ArrayList<>();

                String medicinesStr = resultSet.getString("medicines");
                List<String> medicines = medicinesStr != null ? Arrays.asList(medicinesStr.split(",")) : new ArrayList<>();

                patients.add(new Patient(
                        resultSet.getInt("patientId"),
                        resultSet.getString("patientName"),
                        resultSet.getString("patientGender"),
                        resultSet.getInt("patientAge"),
                        resultSet.getString("medicalProblem"),
                        resultSet.getString("contactDetails"),
                        medicalTests,
                        medicines

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public boolean updatePatientTestsAndMedicines(int patientId, List<String> medicalTests, List<String> medicines) {
        String query = "UPDATE patient SET medicalTests = ?, medicines = ? WHERE patientId = ?";
        // Updates a patient's medical tests and medicines in the database.
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String medicalTestsStr = medicalTests.isEmpty() ? "" : String.join(", ", medicalTests);
            String medicinesStr = medicines.isEmpty() ? "" : String.join(", ", medicines);

            preparedStatement.setString(1, medicalTestsStr);
            preparedStatement.setString(2, medicinesStr);
            preparedStatement.setInt(3, patientId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient's medical tests and medicines updated successfully.");
                return true;  // Indicate the update was successful
            } else {
                System.out.println("No patient found with the given ID.");
                return false;  // Indicate that no update was made because the patient wasn't found
            }
        } catch (SQLException e) {
            System.out.println("Failed to update medical tests and medicines: " + e.getMessage());
            return false;  // Indicate the update failed
        }
    }


    @Override
    public void deletePatient(int patientId) throws PatientNotFoundException {
        String query = "DELETE FROM patient WHERE patientId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new PatientNotFoundException("Patient with ID " + patientId + " not found for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> filterPatientsByAge(int patientAge, String criteria) {

        // Filters patients by age based on the specified criteria (e.g., greater than, less than).
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE patientAge " + criteria + " ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientAge);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String medicalTestsStr = rs.getString("medicalTests");
                List<String> medicalTests = medicalTestsStr != null ? Arrays.asList(medicalTestsStr.split(",")) : new ArrayList<>();

                String medicinesStr = rs.getString("medicines");
                List<String> medicines = medicinesStr != null ? Arrays.asList(medicinesStr.split(",")) : new ArrayList<>();

                Patient patient = new Patient(
                        rs.getInt("patientId"),
                        rs.getString("patientName"),
                        rs.getString("patientGender"),
                        rs.getInt("patientAge"),
                        rs.getString("medicalProblem"),
                        rs.getString("contactDetails"),
                        medicalTests,
                        medicines
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

}
