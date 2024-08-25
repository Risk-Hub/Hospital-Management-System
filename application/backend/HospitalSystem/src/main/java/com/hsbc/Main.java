package com.hsbc;

import com.hsbc.model.*;
import com.hsbc.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to HSBC Hospital!!");
        HospitalStaff hospitalStaff = new HospitalStaff();
        System.out.println("Enter your login Id: ");
        String loginId = scanner.next();
        System.out.println("Enter you password: ");
        String password = scanner.next();
        try {
            if (loginService.validateUser(loginId, password)) {
                hospitalStaff = loginService.retrieveUser(loginId);
                int flag;
                switch (hospitalStaff.getStaffType()) {
                    case "Admin":
                        Admin admin =loginService.getAdminByLoginId(hospitalStaff.getLoginId());
                        AdminService adminService = new AdminServiceImpl();
                        flag = 1;
                        while (flag == 1) {
                            System.out.println("<----------------------------------------->");
                            System.out.println("What operation do you want to perform?");
                            System.out.println("1. Generate Reports");
                            System.out.println("2. View details of all Doctors");
                            System.out.println("3. Check schedule of a doctor");
                            System.out.println("4. Cancel appointments for a specific doctor");
                            System.out.println("5. List All Patients");
                            System.out.println("6. Filter patients by gender");
                            System.out.println("7. Filter patients according to the age group");
                            System.out.println("8. Add a doctor");
                            System.out.println("9. Exit");
                            System.out.println("Enter your choice: ");
                            int choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("----****----");
                                    System.out.println("Which report do you want to generate?");
                                    System.out.println("1. Appointments by specific doctor");
                                    System.out.println("2. All appointments for a day");
                                    System.out.println("3. Number of visits by a patient");
                                    System.out.println("4. Number of patients in the same age group");
                                    System.out.println("5. Number of patients of specific gender");
                                    System.out.println("6. Exit");
                                    System.out.println("Enter your choice-");
                                    int choiceOfReport = scanner.nextInt();
                                    switch (choiceOfReport) {
                                        case 1:
                                            System.out.println("Enter the doctorId:");
                                            int doctorId = scanner.nextInt();
                                            try {
                                                if (adminService.generateAppointmentsByDoctorReport(doctorId)) {
                                                    System.out.println("Reports generated successfully!!");
                                                } else
                                                    System.out.println("No such appointments!!");
                                            } catch (SQLException e) {
                                                System.out.println("Problem retrieving the Data!!");
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Problem connecting to the database!!");
                                            } catch (IOException e) {
                                                System.out.println("Problem generating the report");
                                            }
                                            break;
                                        case 2:
                                            try {
                                                if (adminService.generateAppointmentsForDayReport(LocalDate.now())) {
                                                    System.out.println("Reports generated successfully!!");
                                                }
                                            } catch (SQLException e) {
                                                System.out.println("Problem retrieving the Data!!");
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Problem connecting to the database!!");
                                            } catch (IOException e) {
                                                System.out.println("Problem generating the report");
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Enter a patientId:");
                                            int patientId = scanner.nextInt();
                                            try {
                                                if (adminService.generatePatientVisitsReport(patientId)) {
                                                    System.out.println("Reports generated successfully!");
                                                }
                                            } catch (SQLException e) {
                                                System.out.println("Problem retrieving the Data!!");
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Problem connecting to the database!!");
                                            } catch (IOException e) {
                                                System.out.println("Problem generating the report");
                                            }
                                            break;
                                        case 4:
                                            System.out.println("Enter the minimum age:");
                                            int minAge = scanner.nextInt();
                                            System.out.println("Enter the maximum age:");
                                            int maxAge = scanner.nextInt();
                                            try {
                                                if (adminService.generatePatientsInAgeGroupReport(minAge, maxAge)) {
                                                    System.out.println("Reports generated successfully");
                                                }
                                            } catch (SQLException e) {
                                                System.out.println("Problem retrieving the Data!!");
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Problem connecting to the database!!");
                                            } catch (IOException e) {
                                                System.out.println("Problem generating the report");
                                            }
                                            break;
                                        case 5:
                                            System.out.println("Enter the gender:");
                                            String gender = scanner.next();
                                            try {
                                                if (adminService.generatePatientsByGenderReport(gender)) {
                                                    System.out.println("Reports generated successfully");
                                                }
                                            } catch (SQLException e) {
                                                System.out.println("Problem retrieving the Data!!");
                                            } catch (ClassNotFoundException e) {
                                                System.out.println("Problem connecting to the database!!");
                                            } catch (IOException e) {
                                                System.out.println("Problem generating the report");
                                            }
                                        case 6:
                                            System.out.println("Leaving this page!!");
                                            break;
                                        default:
                                            System.out.println("Invalid choice!!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("----****----");
                                    adminService.allDoctors().stream().forEach(doctor -> System.out.println(doctor));
                                    break;
                                case 3:
                                    System.out.println("----****----");
                                    System.out.println("Enter doctor's id:");
                                    int doctorId = scanner.nextInt();
                                    adminService.scheduleOfDoctor(doctorId).stream().forEach(appointment -> System.out.println(appointment));
                                    break;
                                case 4:
                                    System.out.println("----****----");
                                    System.out.println("Enter ID of the doctor");
                                    int id = scanner.nextInt();
                                    if (adminService.cancelAppointmentsForCurrentDay(id))
                                        System.out.println("Successfully cancelled appointments for today!!");
                                    else
                                        System.out.println("Cancellation unsuccessful!!");
                                    break;
                                case 5:
                                    System.out.println("----****----");
                                    adminService.listAllPatients().stream().forEach(patient -> System.out.println(patient));
                                    break;
                                case 6:
                                    System.out.println("----****----");
                                    System.out.println("Enter the gender:");
                                    String gender = scanner.next();
                                    adminService.patientsFilteredByGender(gender).stream().forEach(patient -> System.out.println(patient));
                                    break;
                                case 7:
                                    System.out.println("----****----");
                                    System.out.println("Enter the minimum & maximum age:");
                                    int minAge = scanner.nextInt();
                                    int maxAge = scanner.nextInt();
                                    adminService.patientsInAnAgeGroup(minAge, maxAge).stream().forEach(patient -> System.out.println(patient));
                                    break;
                                case 8:
                                    System.out.println("----****----");
                                    System.out.println("Enter the filepath:");
                                    String filePath = scanner.next();
                                    if (adminService.importDoctor(filePath)) {
                                        System.out.println("Added to the system successfully");
                                    } else
                                        System.out.println("Already exists in the system!!");
                                    break;
                                case 9:
                                    System.out.println("Exiting the application");
                                    flag = 0;
                                    break;
                                default:
                                    System.out.println("Invalid choice!!");
                            }
                        }
                        break;
                    case "User":
                        User user = loginService.getUserByLoginId(loginId);
                        UserService userService = new UserServiceImpl();
                        flag = 1;
                        while (flag == 1) {
                            System.out.println("What operation do you want to perform?");
                            System.out.println("1. Register a patient");
                            System.out.println("2. Book an appointment");
                            System.out.println("3. View All Appointments");
                            System.out.println("4. Exit");
                            int choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.println("----****----");
                                    System.out.println("Enter the patient details:");
                                    System.out.println("Patient ID:");
                                    int patientId = scanner.nextInt();
                                    System.out.println("Name:");
                                    String patientName = scanner.next();
                                    System.out.println("Contact Number:");
                                    Long patientContact = scanner.nextLong();
                                    ;
                                    System.out.println("Age:");
                                    int patientAge = scanner.nextInt();
                                    System.out.println("Gender:");
                                    String patientGender = scanner.next();
                                    System.out.println("Disease:");
                                    String disease = scanner.next();
                                    System.out.println("Doctor Id:");
                                    int patientDoctor = scanner.nextInt();
                                    Patient newPatient = new Patient(patientId, patientName, patientContact, patientAge, patientGender, disease, patientDoctor);
                                    if (userService.addNewPatient(newPatient))
                                        System.out.println("Registered Successfully");
                                    else
                                        System.out.println("Problem while registering");
                                    break;
                                case 2:
                                    System.out.println("----****----");
                                    System.out.println("Enter the doctor Id:");
                                    int doctorId = scanner.nextInt();
                                    System.out.println("Enter the appointment date (YYYY-MM-DD):");
                                    String dateInput = scanner.next();
                                    LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                                    System.out.println("Enter the timeslot:");
                                    String timeslot = scanner.next();
                                    System.out.println("Enter the patientId:");
                                    int patientsId = scanner.nextInt();
                                    Appointment appointment = new Appointment(doctorId, date, timeslot, false, patientsId);
                                    if (userService.bookAnAppointment(appointment))
                                        System.out.println("Booked!!");
                                    else
                                        System.out.println("Appointment unavailable");
                                    break;
                                case 3:
                                    System.out.println("----****----");
                                    userService.allAppointments().stream().forEach(appointment1 -> System.out.println(appointment1));
                                    break;
                                case 4:
                                    System.out.println("Thank-you!! Exiting the application");
                                    flag = 0;
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }
                        }
                        break;
                    case "Doctor":
                        Doctor doctor = loginService.getDoctorByLoginId(loginId);
                        DoctorService doctorService = new DoctorServiceImpl();
                        flag = 1;
                        while (flag == 1) {
                            System.out.println("Enter the operation choice:");
                            System.out.println("1. Add schedule");
                            System.out.println("2. Cancel Appointment");
                            System.out.println("3. List All appointments");
                            System.out.println("4. Suggest medical tests");
                            System.out.println("5. Suggest medicines");
                            System.out.println("6. Exit");
                            int ch = scanner.nextInt();
                            switch (ch) {
                                case 1:
                                    System.out.println("----****----");
                                    System.out.println("Enter the appointment date(YYYY-MM-DD):");
                                    String inputDate = scanner.next();
                                    LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
                                    System.out.println("Enter the timeslot:");
                                    String timeslot = scanner.next();
                                    System.out.println("Is slot available:");
                                    boolean isAvailable = scanner.nextBoolean();
                                    if (!isAvailable) {
                                        System.out.println("Enter patient ID:");
                                        int patientID = scanner.nextInt();
                                        Appointment appointment = new Appointment(doctor.getDoctorId(), date, timeslot, isAvailable, patientID);
                                        if (doctorService.addSchedule(appointment, doctor)) {
                                            System.out.println("Successful");
                                        } else {
                                            System.out.println("Unsuccessful");
                                        }
                                    } else {
                                        int patientId = 0;
                                        Appointment appointment = new Appointment(doctor.getDoctorId(), date, timeslot, isAvailable, patientId);
                                        if (doctorService.addSchedule(appointment, doctor)) {
                                            System.out.println("Successful");
                                        } else {
                                            System.out.println("Unsuccessful");
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.println("----****----");
                                    System.out.println("Enter the appointment date(YYYY-MM-DD):");
                                    String inputDate2 = scanner.next();
                                    LocalDate date2 = LocalDate.parse(inputDate2, DateTimeFormatter.ISO_LOCAL_DATE);
                                    System.out.println("Enter the timeslot:");
                                    String timeslot2 = scanner.next();
                                    Appointment appointment = new Appointment(doctor.getDoctorId(), date2, timeslot2, false, 0);
                                    if (doctorService.cancelAppointment(appointment, doctor)) {
                                        System.out.println("Successful");
                                    } else {
                                        System.out.println("Unsuccessful");
                                    }
                                    break;
                                case 3:
                                    System.out.println("----****----");
                                    doctorService.getAllAppointment(doctor).stream().forEach(d-> System.out.println(d));
                                    break;
                                case 4:
                                    System.out.println("----****----");
                                    System.out.println("Enter disease name:");
                                    String diseaseName= scanner.next();
                                    Patient p=new Patient();
                                    p.setDiseaseName(diseaseName);
                                    doctorService.suggestMedicalTests(p).stream().forEach(s -> System.out.println(s));
                                    break;
                                case 5:
                                    System.out.println("Enter disease name:");
                                    String disease= scanner.next();
                                    Patient p1=new Patient();
                                    p1.setDiseaseName(disease);
                                    doctorService.suggestMedicines(p1).stream().forEach(medicine-> System.out.println(medicine));
                                    break;
                                case 6:
                                    System.out.println("Exiting!!");
                                    flag = 0;
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }
                        }
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem retrieving the data");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Problem connecting to the database");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem generating reports");
        }
    }
}