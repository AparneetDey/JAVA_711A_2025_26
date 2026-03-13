package com.hospital.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.InvalidAgeException;
import com.hospital.exception.PatientNotFoundException;
import com.hospital.patient.Patient;
import com.hospital.service.HospitalService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ensurePatientFile();

        while (true) {
            System.out.println("\n--- Hospital Management ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println("===========================================================");

            try {
                switch (choice) {
                    case 1 -> addPatient(sc);
                    case 2 -> HospitalService.displayPatients();
                    case 3 -> searchPatient(sc);
                    case 4 -> {
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (InvalidAgeException e) {
                System.out.println("Age Error: " + e.getMessage());
            } catch (DuplicatePatientException e) {
                System.out.println("Patient Data Error: " + e.getMessage());
            } catch (PatientNotFoundException e) {
                System.out.println("Patient  Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading/writing file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void ensurePatientFile() {
        try {
            File patientFile = new File("com/hospital/PatientFile.txt");
            patientFile.getParentFile().mkdirs();
            if (patientFile.createNewFile()) {
                System.out.println("Patient file created.");
            }
        } catch (IOException e) {
            System.out.println("Could not create patient file: " + e.getMessage());
        }
    }

    static void addPatient(Scanner sc) throws Exception {
        System.out.print("Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Disease: ");
        String disease = sc.nextLine();

        Patient p = new Patient(id, name, age, disease);
        HospitalService.addPatient(p);
        System.out.println("Patient added successfully.");
    }

    static void searchPatient(Scanner sc) throws PatientNotFoundException, IOException {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        HospitalService.searchPatient(id);
    }
}
