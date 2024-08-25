package com.hsbc.wfs6.team2.model;

public class Doctor {

    private int doctorId;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorEmail;
    private String doctorContactNumber;

    Doctor()
    {

    }

    public Doctor(int doctorId, String doctorName, String doctorSpecialization, String doctorEmail, String doctorContactNumber) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorEmail = doctorEmail;
        this.doctorContactNumber = doctorContactNumber;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    public void setDoctorContactNumber(String doctorContactNumber) {
        this.doctorContactNumber = doctorContactNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialization='" + doctorSpecialization + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", doctorContactNumber=" + doctorContactNumber +
                '}';
    }
}
