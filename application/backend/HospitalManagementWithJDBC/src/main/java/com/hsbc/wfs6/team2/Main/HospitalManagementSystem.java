package com.hsbc.wfs6.team2.Main;

import com.hsbc.wfs6.team2.presentationlayer.AdminPresentation;
import com.hsbc.wfs6.team2.presentationlayer.DoctorPresentation;
import com.hsbc.wfs6.team2.presentationlayer.StaffPresentation;

import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Welcome to the Hospital Management System!!");
                System.out.println("------------------------------------");
                System.out.println("1. Admin");
                System.out.println("2. Doctor");
                System.out.println("3. Hospital Staff");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                System.out.println("------------------------------------");

                switch (choice) {
                    case 1:
                        System.out.print("Enter password: ");
                        String adPassword = scanner.next();
                        String adminPassword = "admin@123";
                        if (adPassword.equals(adminPassword)) {
                            new AdminPresentation().displayMenu();
                        } else {
                            System.out.println("Wrong password");
                        }
                        break;
                    case 2:
                        System.out.print("Enter password: ");
                        String docPassword = scanner.next();
                        String doctorPassword = "doctor@123";
                        if (docPassword.equals(doctorPassword)) {
                            new DoctorPresentation().displayMenu();
                        } else {
                            System.out.println("Wrong password");
                        }
                        break;
                    case 3:
                        System.out.print("Enter password: ");
                        String staffPassword = scanner.next();
                        String actualPassword = "staff@123";
                        if (staffPassword.equals(actualPassword)) {
                            new StaffPresentation().displayMenu();
                        } else {
                            System.out.println("Wrong password");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                return;
            }
        }
    }
}
