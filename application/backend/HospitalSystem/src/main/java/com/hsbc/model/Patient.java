package com.hsbc.model;

public class Patient {
    private int patientId;
    private String patientName;
    private long patientContactNo;
    private int patientAge;
    private String patientGender;
    private String diseaseName;
    private int doctorId;

    public Patient(int patientId, String patientName, long patientContactNo, int patientAge, String patientGender, String diseaseName,int doctorId) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientContactNo = patientContactNo;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.diseaseName = diseaseName;
        this.doctorId = doctorId;
    }

    public Patient() {
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

    public long getPatientContactNo() {
        return patientContactNo;
    }

    public void setPatientContactNo(long patientContactNo) {
        this.patientContactNo = patientContactNo;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientContactNo=" + patientContactNo +
                ", patientAge=" + patientAge +
                ", patientGender='" + patientGender + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
