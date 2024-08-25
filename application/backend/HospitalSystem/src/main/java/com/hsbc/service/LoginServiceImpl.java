package com.hsbc.service;

import com.hsbc.dao.DatabaseConnect;
import com.hsbc.dao.LoginDao;
import com.hsbc.dao.LoginDaoImpl;
import com.hsbc.model.Admin;
import com.hsbc.model.Doctor;
import com.hsbc.model.HospitalStaff;
import com.hsbc.model.User;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginService{
    public LoginDao loginDao=new LoginDaoImpl();
    @Override
    public boolean validateUser(String loginId, String password) throws SQLException, ClassNotFoundException {
        return loginDao.validateUser(loginId,password);
    }

    @Override
    public HospitalStaff retrieveUser(String loginId) throws SQLException, ClassNotFoundException {
        return loginDao.retrieveUser(loginId);
    }

    @Override
    public Admin getAdminByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        return loginDao.getAdminByLoginId(loginId);
    }

    @Override
    public User getUserByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        return loginDao.getUserByLoginId(loginId);
    }

    @Override
    public Doctor getDoctorByLoginId(String loginId) throws SQLException, ClassNotFoundException {
        return loginDao.getDoctorByLoginId(loginId);
    }
}
