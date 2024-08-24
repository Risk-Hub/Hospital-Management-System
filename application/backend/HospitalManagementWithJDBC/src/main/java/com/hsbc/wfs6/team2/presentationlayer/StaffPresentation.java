package com.hsbc.wfs6.team2.presentationlayer;


import com.hsbc.wfs6.team2.exceptions.PatientNotFoundException;
import com.hsbc.wfs6.team2.model.Appointment;
import com.hsbc.wfs6.team2.model.Doctor;
import com.hsbc.wfs6.team2.model.Patient;
import com.hsbc.wfs6.team2.model.Schedule;
import com.hsbc.wfs6.team2.servicelayers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class StaffPresentation {
    private DoctorService doctorService = new DoctorServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() throws PatientNotFoundException {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Staff Panel");
            System.out.println("------------------------------------");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Doctors");
            System.out.println("3. Book an Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllDoctors();
                    break;
                case 3:
                    bookAppointment();
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPatient() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter Patient Gender: ");
        String patientGender = scanner.nextLine();

        System.out.print("Enter Patient Age: ");
        int patientAge = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Patient Medical History: ");
        String medicalProblem = scanner.nextLine();

        System.out.print("Enter Patient Contact Details: ");
        String contactDetails = scanner.nextLine();

        System.out.print("Patient Medical Tests: ");
        List<String> medicalTests = new ArrayList<>();

        System.out.print("Patient Medicine's prescribed: ");
        List<String> medicines = new ArrayList<>();


        Patient patient = new Patient(patientId, patientName, patientGender, patientAge, medicalProblem, contactDetails, medicalTests, medicines);
        patientService.addPatient(patient);
        System.out.println("Patient added successfully!");
    }

    private void viewAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }


    private void bookAppointment() throws PatientNotFoundException {

        Map<String, String> problemToSpecializationMap = new HashMap<>();
        problemToSpecializationMap.put("Cardiac Issues", "Cardiology");
        problemToSpecializationMap.put("Diabetes", "Endocrinology");
        problemToSpecializationMap.put("Skin Rash", "Dermatology");
        problemToSpecializationMap.put("Flu", "General Physician");

        try {
            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            Patient patient = patientService.getPatientByPatientId(patientId);
            if (patient == null) {
                System.out.println("Patient not found with ID: " + patientId);
                displayMenu();
               return;
            }

            System.out.println("Patient Details: " + patient);
            String medicalProblem = patient.getmedicalProblem();
            System.out.println("Patient's Medical Problem: " + medicalProblem);

            // Fetch the doctor's specialization based on the medical problem
            String requiredSpecialization = problemToSpecializationMap.get(medicalProblem);
            if (requiredSpecialization == null) {
                System.out.println("No specialization found for the medical problem: " + medicalProblem);
                displayMenu();
                return;
            }

            // Retrieve doctors based on specialization
            List<Doctor> suitableDoctors = doctorService.getDoctorsBySpecialization(requiredSpecialization);
            if (suitableDoctors.isEmpty()) {
                System.out.println("No suitable doctors found for the specialization: " + requiredSpecialization);
                displayMenu();
                return;
            }

            System.out.println("Suitable Doctors:");
            for (Doctor doctor : suitableDoctors) {
                System.out.println("Doctor ID: " + doctor.getDoctorId() + ", Name: " + doctor.getDoctorName() + ", Specialization: " + doctor.getDoctorSpecialization());
            }

            System.out.print("Enter Doctor ID from the above list: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            Doctor selectedDoctor = suitableDoctors.stream()
                    .filter(doc -> doc.getDoctorId() == doctorId)
                    .findFirst()
                    .orElse(null);

            if (selectedDoctor == null) {
                System.out.println("Invalid Doctor ID. Please choose from the list.");
                return;
            }
            System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            LocalDate appointmentDate = LocalDate.parse(dateStr);

//            System.out.print("Enter Appointment Time (HH:mm): ");
//            String timeStr = scanner.nextLine();
//            LocalTime appointmentTime = LocalTime.parse(timeStr);

            LocalTime appointmentTime = null;
            boolean validTime = false;

            while (!validTime) {
                try {
                    System.out.println("Available time slots:");
                    System.out.println("1. Morning: 08:30 AM - 12:00 PM");
                    System.out.println("2. Afternoon: 12:00 PM - 03:30 PM");
                    System.out.println("3. Evening: 03:30 PM - 06:00 PM");
                    System.out.print("Choose slot (1, 2, or 3): ");
                    int ch = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    LocalTime slotStartTime = null;
                    LocalTime slotEndTime = null;

                    switch (ch) {
                        case 1:
                            slotStartTime = LocalTime.of(8, 30);
                            slotEndTime = LocalTime.of(12, 0);
                            break;
                        case 2:
                            slotStartTime = LocalTime.of(12, 0);
                            slotEndTime = LocalTime.of(15, 30);
                            break;
                        case 3:
                            slotStartTime = LocalTime.of(15, 30);
                            slotEndTime = LocalTime.of(18, 0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose a valid slot.");
                            return;
                    }

                    System.out.print("Enter Appointment Time (HH:mm) within the chosen slot: ");
                    String timeStr = scanner.nextLine();
                    appointmentTime = LocalTime.parse(timeStr);

                    if (appointmentTime.isAfter(slotStartTime) && appointmentTime.isBefore(slotEndTime) ||
                            appointmentTime.equals(slotStartTime) || appointmentTime.equals(slotEndTime)) {
                        validTime = true;
                    } else {
                        System.out.println("Time does not match the selected slot. Please enter a time within the selected slot.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please enter the time in HH:mm format.");
                }

            }

            Appointment appointment = new Appointment(0, patientId, doctorId, appointmentDate, appointmentTime, "Scheduled");
            appointmentService.addAppointment(appointment);
            System.out.println("Appointment booked successfully!");
        } catch (PatientNotFoundException e) {
            throw new PatientNotFoundException(e.getMessage());
        }

    }

    private boolean isValidSlot(LocalTime time) {
        LocalTime morningStart = LocalTime.of(8, 30);
        LocalTime morningEnd = LocalTime.of(12, 0);
        LocalTime afternoonStart = LocalTime.of(12, 0);
        LocalTime afternoonEnd = LocalTime.of(15, 30);
        LocalTime eveningStart = LocalTime.of(15, 30);
        LocalTime eveningEnd = LocalTime.of(18, 0);

        return (time.isAfter(morningStart) && time.isBefore(morningEnd)) ||
                (time.equals(morningStart) || time.equals(morningEnd)) ||
                (time.isAfter(afternoonStart) && time.isBefore(afternoonEnd)) ||
                (time.equals(afternoonStart) || time.equals(afternoonEnd)) ||
                (time.isAfter(eveningStart) && time.isBefore(eveningEnd)) ||
                (time.equals(eveningStart) || time.equals(eveningEnd));
    }


    private void viewAppointments() {
        try {
            System.out.print("Enter Doctor ID: ");
            int doctorId = scanner.nextInt();

            List<Schedule> schedules = doctorService.getDoctorSchedules(doctorId);
            if (schedules.isEmpty()) {
                System.out.println("No schedule found for this doctor.");
            } else {
                System.out.println("Doctor's Schedule:");
                for (Schedule schedule : schedules) {
                    System.out.println("Start Time: " + schedule.getStartTime());
                    System.out.println("End Time: " + schedule.getEndTime());
                }
            }

            List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
            if (appointments.isEmpty()) {
                System.out.println("No appointments found.");
            } else {
                for (Appointment appointment : appointments) {
                    System.out.println(appointment);
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing appointments: " + e.getMessage());
        }
    }

}

