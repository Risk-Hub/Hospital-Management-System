package com.hsbc.wfs6.team2.presentationlayer;

import com.hsbc.wfs6.team2.exceptions.DoctorNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Patient;
import com.hsbc.wfs6.team2.model.Schedule;
import com.hsbc.wfs6.team2.servicelayers.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

public class AdminPresentation {
    private DoctorService doctorService = new DoctorServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() throws DoctorNotFoundException {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Admin Panel");
            System.out.println("------------------------------------");
            System.out.println("1. Add Doctor");
            System.out.println("2. Show All Doctors");
            System.out.println("3. See, Update, and Cancel Appointments for Today");
            System.out.println("4. Show All Patients");
            System.out.println("5. Filter Patients by Age");
            System.out.println("6. Delete Doctor");
            System.out.println("7. Update Doctor");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    showAllDoctors();
                    break;
                case 3:
                    manageAppointmentsForToday();
                    break;
                case 4:
                    showAllPatients();
                    break;
                case 5:
                    filterPatientsByAge();
                    break;
                case 6:
                    deleteDoctor();
                    break;

                case 7:
                    updateDoctor();
                    break;

                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void updateDoctor() {
        System.out.print("Enter Doctor ID to update: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Doctor Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter new Doctor Email Id: ");
        String emailId = scanner.nextLine();

        System.out.print("Enter new Doctor Phone Number: ");
        String doctorContact = scanner.nextLine();

        Doctor updatedDoctor = new Doctor(doctorId, name, specialization, emailId, doctorContact);
        doctorService.updateDoctor(updatedDoctor);
        System.out.println("Doctor updated successfully.");
    }


    private void deleteDoctor() {
        System.out.print("Enter Doctor ID to delete: ");
        int doctorId = scanner.nextInt();
        try {
            doctorService.deleteDoctor(doctorId);
        } catch (DoctorNotFoundException e) {
            System.out.println("doctor not found");        }
        System.out.println("Doctor deleted successfully.");
    }


    private void filterPatientsByAge() {
        System.out.print("Enter age to filter by: ");
        int age = scanner.nextInt();
        System.out.print("Enter criteria (Age >, Age <, Age =): ");
        scanner.nextLine();
        String criteria = scanner.nextLine();

        List<Patient> filteredPatients = patientService.filterPatientsByAge(age, criteria);
        if (filteredPatients.isEmpty()) {
            System.out.println("No patients found matching the criteria.");
        } else {
            for (Patient patient : filteredPatients) {
                System.out.println(patient);
            }
        }
    }


    private void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Doctor Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Doctor email id: ");
        String emailId = scanner.nextLine();

        System.out.print("Enter Doctor's contact number: ");
        String doctorContact = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, specialization, emailId, doctorContact);
        doctorService.addDoctor(doctor);

        System.out.println("Doctor added successfully.");
    }

    private void showAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
                List<Schedule> schedules = doctorService.getDoctorSchedules(doctor.getDoctorId());
                for (Schedule schedule : schedules) {
                    System.out.println(schedule);
                }
            }
        }
    }

    private void manageAppointmentsForToday() {
        System.out.print("Enter doctor id: ");
        int doctorId = scanner.nextInt();
        LocalDate today = LocalDate.now();
        List<Appointment> appointments = appointmentService.getAppointmentsForDay(doctorId, today);

        if (appointments.isEmpty()) {
            System.out.println("No appointments for today.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }

        System.out.println("1. Update Appointment");
        System.out.println("2. Cancel Appointment");
        System.out.println("3. Cancel All Appointments for Today");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                updateAppointment();
                break;
            case 2:
                cancelAppointment();
                break;
            case 3:
                cancelAllAppointmentsForToday();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void updateAppointment() {
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter patient id: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter doctor id: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate appointmentDate = LocalDate.parse(dateStr);

        System.out.print("Enter new Time (HH:MM): ");
        String timeStr = scanner.nextLine();
        LocalTime appointmentTime = LocalTime.parse(timeStr);

        String status = "";
        while (true) {
            System.out.println("Enter new status (1 for Scheduled, 2 for Cancelled): ");
            int statusChoice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            if (statusChoice == 1) {
                status = "Scheduled";
                break;
            } else if (statusChoice == 2) {
                status = "Cancelled";
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 for Scheduled or 2 for Cancelled.");
            }
        }

        Appointment updatedAppointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, appointmentTime, status);
        appointmentService.updateAppointment(updatedAppointment);

        System.out.println("Appointment updated successfully.");
    }

    private void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        appointmentService.deleteAppointment(appointmentId);
        System.out.println("Appointment canceled successfully.");
    }

    private void cancelAllAppointmentsForToday() {
        System.out.print("Enter doctor id: ");
        int doctorId = scanner.nextInt();
        LocalDate today = LocalDate.now();
        List<Appointment> appointments = appointmentService.getAppointmentsForDay(doctorId,today);

        for (Appointment appointment : appointments) {
            appointmentService.deleteAppointment(appointment.getAppointmentId());
        }

        System.out.println("All appointments for today have been canceled.");
    }

    private void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println("Patient ID: " + patient.getPatientId());
                System.out.println("Name: " + patient.getPatientName());
                System.out.println("Gender: " + patient.getPatientGender());
                System.out.println("Age: " + patient.getPatientAge());
                System.out.println("Medical Problem: " + patient.getmedicalProblem());

                List<String> medicalTests = patient.getMedicalTests();
                List<String> medicines = patient.getMedicines();

                System.out.println("Medical Tests: " + (medicalTests.isEmpty() ? "Not added yet" : String.join(", ", medicalTests)));
                System.out.println("Medicines: " + (medicines.isEmpty() ? "Not added yet" : String.join(", ", medicines)));
            }
        }

    }


}


