package com.hsbc;

import com.hsbc.dao.AdminDao;
import com.hsbc.model.Appointment;
import com.hsbc.model.Doctor;
import com.hsbc.model.Patient;
import com.hsbc.service.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AdminServiceImplTest {

    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateAppointmentsByDoctorReport() throws SQLException, ClassNotFoundException, IOException {
        int doctorId = 1;
        when(adminDao.generateAppointmentsByDoctorReport(doctorId)).thenReturn(true);

        boolean result = adminService.generateAppointmentsByDoctorReport(doctorId);
        assertTrue(result);
    }

    @Test
    public void testGenerateAppointmentsForDayReport() throws SQLException, ClassNotFoundException, IOException {
        LocalDate date = LocalDate.now();
        when(adminDao.generateAppointmentsForDayReport(date)).thenReturn(true);

        boolean result = adminService.generateAppointmentsForDayReport(date);
        assertTrue(result);
    }

    @Test
    public void testGeneratePatientVisitsReport() throws SQLException, ClassNotFoundException, IOException {
        int patientId = 1;
        when(adminDao.generatePatientVisitsReport(patientId)).thenReturn(true);

        boolean result = adminService.generatePatientVisitsReport(patientId);
        assertTrue(result);
    }

    @Test
    public void testGeneratePatientsInAgeGroupReport() throws SQLException, ClassNotFoundException, IOException {
        int minAge = 20;
        int maxAge = 30;
        when(adminDao.generatePatientsInAgeGroupReport(minAge, maxAge)).thenReturn(true);

        boolean result = adminService.generatePatientsInAgeGroupReport(minAge, maxAge);
        assertTrue(result);
    }

    @Test
    public void testGeneratePatientsByGenderReport() throws SQLException, ClassNotFoundException, IOException {
        String gender = "Male";
        when(adminDao.generatePatientsByGenderReport(gender)).thenReturn(true);

        boolean result = adminService.generatePatientsByGenderReport(gender);
        assertTrue(result);
    }

    @Test
    public void testAllDoctors() throws SQLException, ClassNotFoundException {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
        when(adminDao.allDoctors()).thenReturn(doctors);

        List<Doctor> result = adminService.allDoctors();
        assertTrue(result.size() == 2);
    }

    @Test
    public void testScheduleOfDoctor() throws SQLException, ClassNotFoundException {
        int doctorId = 1;
        List<Appointment> appointments = Arrays.asList(new Appointment(), new Appointment());
        when(adminDao.scheduleOfDoctor(doctorId)).thenReturn(appointments);

        List<Appointment> result = adminService.scheduleOfDoctor(doctorId);
        assertTrue(result.size() == 2);
    }

    @Test
    public void testCancelAppointmentsForCurrentDay() throws SQLException, ClassNotFoundException {
        int doctorId = 1;
        when(adminDao.cancelAppointmentsForCurrentDay(doctorId)).thenReturn(true);

        boolean result = adminService.cancelAppointmentsForCurrentDay(doctorId);
        assertTrue(result);
    }

    @Test
    public void testListAllPatients() throws SQLException, ClassNotFoundException {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(adminDao.listAllPatients()).thenReturn(patients);

        List<Patient> result = adminService.listAllPatients();
        assertTrue(result.size() == 2);
    }

    @Test
    public void testPatientsFilteredByGender() throws SQLException, ClassNotFoundException {
        String gender = "Female";
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(adminDao.patientsFilteredByGender(gender)).thenReturn(patients);

        List<Patient> result = adminService.patientsFilteredByGender(gender);
        assertTrue(result.size() == 2);
    }

    @Test
    public void testPatientsInAnAgeGroup() throws SQLException, ClassNotFoundException {
        int age1 = 20;
        int age2 = 30;
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(adminDao.patientsInAnAgeGroup(age1, age2)).thenReturn(patients);

        List<Patient> result = adminService.patientsInAnAgeGroup(age1, age2);
        assertTrue(result.size() == 2);
    }

    @Test
    public void testImportDoctor() throws SQLException, ClassNotFoundException, IOException {
        String filepath = "doctors.csv";
        when(adminDao.importDoctor(filepath)).thenReturn(true);

        boolean result = adminService.importDoctor(filepath);
        assertTrue(result);
    }
}
