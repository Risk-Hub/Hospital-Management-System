package com.hsbc;

import com.hsbc.dao.LoginDao;
import com.hsbc.model.Admin;
import com.hsbc.model.Doctor;
import com.hsbc.model.HospitalStaff;
import com.hsbc.model.User;
import com.hsbc.service.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestLoginServiceImpl {

    private LoginServiceImpl loginService;
    private LoginDao loginDaoMock;

    @Before
    public void setUp() {
        loginDaoMock = Mockito.mock(LoginDao.class);
        loginService = new LoginServiceImpl();
        loginService.loginDao = loginDaoMock; // Inject the mock LoginDao into the service
    }

    @Test
    public void testValidateUser() throws SQLException, ClassNotFoundException {
        String loginId = "testUser";
        String password = "password123";

        when(loginDaoMock.validateUser(loginId, password)).thenReturn(true);

        boolean isValid = loginService.validateUser(loginId, password);

        assertTrue(isValid);
        verify(loginDaoMock, times(1)).validateUser(loginId, password);
    }

    @Test
    public void testRetrieveUser() throws SQLException, ClassNotFoundException {
        String loginId = "testUser";
        HospitalStaff mockStaff = new HospitalStaff();
        mockStaff.setLoginId(loginId);

        when(loginDaoMock.retrieveUser(loginId)).thenReturn(mockStaff);

        HospitalStaff staff = loginService.retrieveUser(loginId);

        assertNotNull(staff);
        assertEquals(loginId, staff.getLoginId());
        verify(loginDaoMock, times(1)).retrieveUser(loginId);
    }

    @Test
    public void testGetAdminByLoginId() throws SQLException, ClassNotFoundException {
        String loginId = "adminUser";
        Admin mockAdmin = new Admin();
        mockAdmin.setLoginId(loginId);

        when(loginDaoMock.getAdminByLoginId(loginId)).thenReturn(mockAdmin);

        Admin admin = loginService.getAdminByLoginId(loginId);

        assertNotNull(admin);
        assertEquals(loginId, admin.getLoginId());
        verify(loginDaoMock, times(1)).getAdminByLoginId(loginId);
    }

    @Test
    public void testGetUserByLoginId() throws SQLException, ClassNotFoundException {
        String loginId = "normalUser";
        User mockUser = new User();
        mockUser.setLoginId(loginId);

        when(loginDaoMock.getUserByLoginId(loginId)).thenReturn(mockUser);

        User user = loginService.getUserByLoginId(loginId);

        assertNotNull(user);
        assertEquals(loginId, user.getLoginId());
        verify(loginDaoMock, times(1)).getUserByLoginId(loginId);
    }

    @Test
    public void testGetDoctorByLoginId() throws SQLException, ClassNotFoundException {
        String loginId = "doctorUser";
        Doctor mockDoctor = new Doctor();
        mockDoctor.setLoginId(loginId);

        when(loginDaoMock.getDoctorByLoginId(loginId)).thenReturn(mockDoctor);

        Doctor doctor = loginService.getDoctorByLoginId(loginId);

        assertNotNull(doctor);
        assertEquals(loginId, doctor.getLoginId());
        verify(loginDaoMock, times(1)).getDoctorByLoginId(loginId);
    }
}
