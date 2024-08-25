package com.hsbc.wfs6.team2.daolayers;

import com.hsbc.wfs6.team2.exceptions.PatientNotFoundException;
import com.hsbc.wfs6.team2.model.Patient;

import java.util.List;

public interface PatientDao {

    void addPatient(Patient patient);
    Patient getPatientById(int patientId) throws PatientNotFoundException;
    List<Patient> getAllPatients();
    boolean updatePatientTestsAndMedicines(int patientId, List<String> medicalTests, List<String> medicines);
    void deletePatient(int patientId) throws PatientNotFoundException;
    List<Patient> filterPatientsByAge(int patientAge, String criteria);
}
