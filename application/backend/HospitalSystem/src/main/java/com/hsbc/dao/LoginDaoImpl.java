package com.hsbc.dao;

import com.hsbc.model.Admin;
import com.hsbc.model.Doctor;
import com.hsbc.model.HospitalStaff;
import com.hsbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean validateUser(String loginId, String password) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from staffLoginCredentials where loginId like ?");
            preparedStatement.setString(1, loginId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String retrievedPassword = rs.getString("userPassword");
                return retrievedPassword.equals(password);
            }
        }
        return false;
    }

    @Override
    public HospitalStaff retrieveUser(String loginId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from staffLoginCredentials where loginId like ?");
            preparedStatement.setString(1, loginId);
            ResultSet rs = preparedStatement.executeQuery();
            HospitalStaff hospitalStaff = new HospitalStaff();
            while (rs.next()) {
                hospitalStaff.setLoginId(loginId);
                hospitalStaff.setPassword(rs.getString("userPassword"));
                hospitalStaff.setStaffType(rs.getString("userType"));
            }
            return hospitalStaff;
        }
    }

    @Override
    public Admin getAdminByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            String sql = "SELECT * FROM admin WHERE loginId = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, loginId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getInt("adminId"));
                    admin.setAdminName(rs.getString("adminName"));
                    admin.setLoginId(rs.getString("loginId"));
                    return admin;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public User getUserByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnect.initializeDb()) {
            String sql = "SELECT * FROM user WHERE loginId = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, loginId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("userId"));
                    user.setUserName(rs.getString("userName"));
                    user.setLoginId(rs.getString("loginId"));
                    return user;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public Doctor getDoctorByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM doctor WHERE loginId = ?";
        try (Connection conn=DatabaseConnect.initializeDb()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Doctor doctor=new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setDoctorName(rs.getString("doctorName"));
                doctor.setDoctorEMail(rs.getString("doctorEmail"));
                doctor.setDoctorContactNo(rs.getLong("doctorContactNo"));
                doctor.setLoginId(rs.getString("loginId"));
                return doctor;
            } else {
                return null;
            }
        }
    }
}

