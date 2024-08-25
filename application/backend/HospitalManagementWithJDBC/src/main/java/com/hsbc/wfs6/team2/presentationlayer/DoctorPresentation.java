package com.hsbc.wfs6.team2.presentationlayer;

import com.hsbc.wfs6.team2.model.Appointment;
import com.hsbc.wfs6.team2.model.Patient;
import com.hsbc.wfs6.team2.model.Schedule;
import com.hsbc.wfs6.team2.servicelayers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DoctorPresentation {
    private DoctorService doctorService = new DoctorServiceImpl();
    private AppointmentService appointmentService = new AppointmentServiceImpl();
    private PatientService patientService = new PatientServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Doctor Panel");
            System.out.println("------------------------------------");
            System.out.println("1. Set Up Schedule for Next Three Days");
            System.out.println("2. View Appointments");
            System.out.println("3. Suggest Medical Tests and Medicines");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    setUpSchedule();
                    break;
                case 2:
                    viewAppointments();
                    break;
                case 3:
                    suggestMedicalTestsAndMedicines();
                    break;
                case 4:
                    cancelAppointment();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void setUpSchedule() {
        System.out.println("-----------------------------");
        try {
            System.out.print("Enter Doctor ID: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < 3; i++) {
                System.out.print("Enter Schedule Date for day " + (i + 1) + " (yyyy-MM-dd): ");
                LocalDate scheduleDate = LocalDate.parse(scanner.nextLine());

                boolean validSlot = false;
                LocalDateTime startDateTime = null;
                LocalDateTime endDateTime = null;

                while (!validSlot) {
                    System.out.println("Available working shifts:");
                    System.out.println("1. Morning: 08:30 AM - 12:00 PM");
                    System.out.println("2. Afternoon: 12:00 PM - 03:30 PM");
                    System.out.println("3. Evening: 03:30 PM - 06:00 PM");
                    System.out.print("Choose slot (1, 2, or 3): ");
                    int ch = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (ch) {
                        case 1:
                            startDateTime = scheduleDate.atTime(8, 30);
                            endDateTime = scheduleDate.atTime(12, 0);
                            break;
                        case 2:
                            startDateTime = scheduleDate.atTime(12, 0);
                            endDateTime = scheduleDate.atTime(15, 30);
                            break;
                        case 3:
                            startDateTime = scheduleDate.atTime(15, 30);
                            endDateTime = scheduleDate.atTime(18, 0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose a valid slot.");
                            continue;
                    }

                    validSlot = true; // Valid slot chosen, exit loop
                }

                Schedule schedule = new Schedule(doctorId, startDateTime, endDateTime);
                doctorService.addSchedule(schedule);
                System.out.println("Schedule added for " + startDateTime.toLocalDate() + " (" + startDateTime.toLocalTime() + " to " + endDateTime.toLocalTime() + ")");
            }
            System.out.println("Schedule set up successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        } catch (Exception e) {
            System.out.println("Error setting up schedule: " + e.getMessage());
        }
    }

    private void viewAppointments() {
        try {
            System.out.println("--------------------------------");
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


    private void suggestMedicalTestsAndMedicines() {
        try {
            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Fetch current patient details before update
            Patient patient = patientService.getPatientByPatientId(patientId);

            if (patient == null) {
                System.out.println("Patient with ID " + patientId + " not found.");
                return;
            }

            System.out.println("Current details for Patient ID " + patientId + ":");
            System.out.println("Name: " + patient.getPatientName());
            System.out.println("Gender: " + patient.getPatientGender());
            System.out.println("Age: " + patient.getPatientAge());
            System.out.println("Medical Problem: " + patient.getmedicalProblem());
            System.out.println("Medical Tests: " + (patient.getMedicalTests().isEmpty() ? "Not added yet" : String.join(", ", patient.getMedicalTests())));
            System.out.println("Medicines: " + (patient.getMedicines().isEmpty() ? "Not added yet" : String.join(", ", patient.getMedicines())));

            System.out.println("Enter Suggested Medical Tests (comma-separated): ");
            String medicalTestsInput = scanner.nextLine();
            List<String> medicalTests = Arrays.asList(medicalTestsInput.split(",\\s*"));

            System.out.print("Enter Suggested Medicines (comma-separated): ");
            String medicinesInput = scanner.nextLine();
            List<String> medicines = Arrays.asList(medicinesInput.split(",\\s*"));

            // Update the patient in the database
            boolean updateSuccess = patientService.updatePatientTestsAndMedicines(patientId, medicalTests, medicines);

            if (updateSuccess) {
                System.out.println("Medical tests and medicines updated successfully.");

                // Fetch updated patient details
                patient = patientService.getPatientByPatientId(patientId);

                // Display updated details
                System.out.println("Updated details for Patient ID " + patientId + ":");
                System.out.println("Name: " + patient.getPatientName());
                System.out.println("Gender: " + patient.getPatientGender());
                System.out.println("Age: " + patient.getPatientAge());
                System.out.println("Medical Problem: " + patient.getmedicalProblem());
                System.out.println("Medical Tests: " + (patient.getMedicalTests().isEmpty() ? "Not added yet" : String.join(", ", patient.getMedicalTests())));
                System.out.println("Medicines: " + (patient.getMedicines().isEmpty() ? "Not added yet" : String.join(", ", patient.getMedicines())));
            } else {
                System.out.println("Failed to update medical tests and medicines.");
            }
        } catch (Exception e) {
            System.out.println("Error suggesting medical tests and medicines: " + e.getMessage());
        }
    }

    private void cancelAppointment() {
        try {
            System.out.print("Enter Appointment ID to cancel: ");
            int appointmentId = scanner.nextInt();

            boolean success = appointmentService.deleteAppointment(appointmentId);
            if (success) {
                System.out.println("Appointment canceled successfully.");
            } else {
                System.out.println("Appointment not found or could not be canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }
}
