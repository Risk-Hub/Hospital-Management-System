package com.hsbc;

import com.hsbc.dao.DoctorDao;
import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;
import com.hsbc.service.DoctorServiceImpl;
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

public class DoctorServiceImplTest {

    @Mock
    private DoctorDao doctorDao;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSchedule() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        when(doctorDao.addSchedule(appointment, doctor)).thenReturn(true);

        boolean result = doctorService.addSchedule(appointment, doctor);
        assertTrue(result);
    }

    @Test
    public void testCancelAppointment() throws SQLException, ClassNotFoundException {
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        when(doctorDao.cancelAppointment(appointment, doctor)).thenReturn(true);

        boolean result = doctorService.cancelAppointment(appointment, doctor);
        assertTrue(result);
    }

    @Test
    public void testGetAllAppointment() throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor();
        List<Appointment> appointments = Arrays.asList(new Appointment(), new Appointment());
        when(doctorDao.viewAppointments(doctor)).thenReturn(appointments);

        List<Appointment> result = doctorService.getAllAppointment(doctor);
        assertEquals(2, result.size());
    }

    @Test
    public void testSuggestMedicalTests() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        List<String> tests = Arrays.asList("Blood Test", "X-Ray");
        when(doctorDao.suggestMedicalTests(patient)).thenReturn(tests);

        List<String> result = doctorService.suggestMedicalTests(patient);
        assertEquals(2, result.size());
        assertEquals("Blood Test", result.get(0));
        assertEquals("X-Ray", result.get(1));
    }

    @Test
    public void testSuggestMedicines() throws SQLException, ClassNotFoundException {
        Patient patient = new Patient();
        List<String> medicines = Arrays.asList("Paracetamol", "Ibuprofen");
        when(doctorDao.suggestMedicines(patient)).thenReturn(medicines);

        List<String> result = doctorService.suggestMedicines(patient);
        assertEquals(2, result.size());
        assertEquals("Paracetamol", result.get(0));
        assertEquals("Ibuprofen", result.get(1));
    }
}
