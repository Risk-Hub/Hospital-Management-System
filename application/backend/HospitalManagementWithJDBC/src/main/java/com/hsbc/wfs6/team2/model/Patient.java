package com.hsbc.wfs6.team2.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private int patientId;
    private String patientName;
    private String patientGender;
    private int patientAge;
    private String medicalProblem;
    private String contactDetails;
    private List<String> medicalTests;
    private List<String> medicines;

    Patient()
    {

    }

    public Patient(int patientId, String patientName, String patientGender, int patientAge, String medicalProblem, String contactDetails, List<String> medicalTests, List<String> medicines) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.medicalProblem = medicalProblem;
        this.contactDetails = contactDetails;
        this.medicalTests = new ArrayList<>();
        this.medicines = new ArrayList<>();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getmedicalProblem() {
        return medicalProblem;
    }

    public void setmedicalProblem(String medicalProblem) {
        this.medicalProblem = medicalProblem;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<String> getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(List<String> medicalTests) {
        this.medicalTests = medicalTests;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAge=" + patientAge +
                ", medicalProblem='" + medicalProblem + '\'' +
                ", contactDetails=" + contactDetails +
                ", medicalTests=" + (medicalTests.isEmpty() ? "Not added yet" : String.join(", ", medicalTests)) +
                ", medicines=" + (medicines.isEmpty() ? "Not added yet" : String.join(", ", medicines)) +
                '}';
    }
}
