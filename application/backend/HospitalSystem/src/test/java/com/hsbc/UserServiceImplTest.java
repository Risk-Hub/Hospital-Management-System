package com.hsbc;

import com.hsbc.dao.UserDao;
import com.hsbc.model.Appointment;
import com.hsbc.model.Patient;
import com.hsbc.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNewPatient() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        when(userDao.createPatient(patient)).thenReturn(true);

        boolean result = userService.addNewPatient(patient);
        assertTrue(result);
    }

    @Test
    public void testBookAnAppointment() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment();
        when(userDao.bookAppointment(appointment)).thenReturn(true);

        boolean result = userService.bookAnAppointment(appointment);
        assertTrue(result);
    }

    @Test
    public void testAllAppointments() throws SQLException, ClassNotFoundException {
        List<Appointment> appointments = Arrays.asList(new Appointment(), new Appointment());
        when(userDao.allAppointments()).thenReturn(appointments);

        List<Appointment> result = userService.allAppointments();
        assertEquals(2, result.size());
    }
}
