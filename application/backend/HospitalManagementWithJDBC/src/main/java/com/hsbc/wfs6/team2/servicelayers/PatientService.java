package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.exceptions.PatientNotFoundException;
import com.hsbc.wfs6.team2.model.Patient;

import java.util.List;

public interface PatientService {

    public void addPatient(Patient patient);

    public Patient getPatientByPatientId(int patientId) throws PatientNotFoundException;

    public List<Patient> getAllPatients();

    public boolean updatePatientTestsAndMedicines(int patientId, List<String> medicalTests, List<String> medicines);

    public void deletePatient(int patientId) throws PatientNotFoundException;

    public List<Patient> filterPatientsByAge(int patientAge, String criteria);
}
