package com.hsbc.dao;

import com.hsbc.model.Admin;
import com.hsbc.model.Doctor;
import com.hsbc.model.HospitalStaff;
import com.hsbc.model.User;

import java.sql.SQLException;

public interface LoginDao {
    boolean validateUser(String loginId,String password) throws SQLException, ClassNotFoundException;
    HospitalStaff retrieveUser(String loginId) throws SQLException, ClassNotFoundException;
    Admin getAdminByLoginId(String loginId) throws SQLException, ClassNotFoundException;
    public User getUserByLoginId(String loginId) throws SQLException, ClassNotFoundException;
    Doctor getDoctorByLoginId(String loginId) throws SQLException, ClassNotFoundException;
}
