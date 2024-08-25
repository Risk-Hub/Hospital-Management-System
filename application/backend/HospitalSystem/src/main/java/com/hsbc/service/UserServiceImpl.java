package com.hsbc.service;

import com.hsbc.dao.UserDao;
import com.hsbc.dao.UserDaoImpl;
import com.hsbc.model.Appointment;
import com.hsbc.model.Patient;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao=new UserDaoImpl();

    @Override
    public boolean addNewPatient(Patient patient) throws SQLException, ClassNotFoundException {
        return userDao.createPatient(patient);
    }

    @Override
    public boolean bookAnAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        return userDao.bookAppointment(appointment);
    }

    @Override
    public List<Appointment> allAppointments() throws SQLException, ClassNotFoundException {
        return userDao.allAppointments();
    }
}
