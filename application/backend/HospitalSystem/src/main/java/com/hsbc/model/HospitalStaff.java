package com.hsbc.model;

public class HospitalStaff {
    private String loginId;
    private String password;
    private String staffType;

    public HospitalStaff() {
    }

    public HospitalStaff(String loginId, String password, String staffType) {
        this.loginId = loginId;
        this.password = password;
        this.staffType = staffType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    @Override
    public String toString() {
        return "HospitalStaff{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", staffType='" + staffType + '\'' +
                '}';
    }
}
