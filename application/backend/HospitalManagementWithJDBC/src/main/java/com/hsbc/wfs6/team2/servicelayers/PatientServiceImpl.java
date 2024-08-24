package com.hsbc.wfs6.team2.servicelayers;

import com.hsbc.wfs6.team2.daolayers.PatientDao;
import com.hsbc.wfs6.team2.daolayers.PatientDaoImpl;
import com.hsbc.wfs6.team2.exceptions.PatientNotFoundException;
import com.hsbc.wfs6.team2.model.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService{
    private PatientDao patientDao = new PatientDaoImpl();

    @Override
    public void addPatient(Patient patient) {
        patientDao.addPatient(patient);
    }

    @Override
    public Patient getPatientByPatientId(int patientId) throws PatientNotFoundException {
        return patientDao.getPatientById(patientId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    @Override
    public  boolean updatePatientTestsAndMedicines(int patientId, List<String> medicalTests, List<String> medicines) {
       return patientDao.updatePatientTestsAndMedicines(patientId, medicalTests, medicines);
    }


    @Override
    public void deletePatient(int patientId) throws PatientNotFoundException {
        patientDao.deletePatient(patientId);
    }

    @Override
    public List<Patient> filterPatientsByAge(int patientAge, String criteria) {
        return patientDao.filterPatientsByAge(patientAge, criteria);
    }
}
