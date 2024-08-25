package com.hsbc.model;

public class Doctor extends HospitalStaff {
    private int doctorId;
    private String doctorName;
    private long doctorContactNo;
    private String doctorEMail;


    public Doctor() {
    }

    public Doctor(String loginId, String password, String personType, int doctorId, String doctorName, long doctorContactNo, String doctorEMail) {
        super(loginId, password, personType);
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorContactNo = doctorContactNo;
        this.doctorEMail = doctorEMail;

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

    public long getDoctorContactNo() {
        return doctorContactNo;
    }

    public void setDoctorContactNo(long doctorContactNo) {
        this.doctorContactNo = doctorContactNo;
    }

    public String getDoctorEMail() {
        return doctorEMail;
    }

    public void setDoctorEMail(String doctorEMail) {
        this.doctorEMail = doctorEMail;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorContactNo=" + doctorContactNo +
                ", doctorEMail='" + doctorEMail + '\'' +
                '}';
    }
}
