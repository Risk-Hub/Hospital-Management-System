package com.hsbc.model;

public class Admin extends HospitalStaff {
    private int adminId;
    private String adminName;

    public Admin() {
    }

    public Admin(String loginId, String password, String personType, int adminId, String adminName) {
        super(loginId, password, personType);
        this.adminId = adminId;
        this.adminName = adminName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
