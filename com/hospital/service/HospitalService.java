package com.hospital.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.hospital.exception.PatientNotFoundException;
import com.hospital.patient.Patient;

public class HospitalService {
    public static void addPatient(Patient P) throws Exception {
        String filePath = "com/hospital/PatientFile.txt";
        
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(P.getter() + "\n");

        fw.close();
    }

    public static void  searchPatient(int id) throws PatientNotFoundException, IOException {
        String filePath = "com/hospital/PatientFile.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 4);
                if (parts.length >= 1 && Integer.parseInt(parts[0]) == id) {
                    System.out.println("Patient Id: " + parts[0]);
                    System.out.println("Patient Name: " + parts[1]);
                    System.out.println("Patient Age: " + parts[2]);
                    System.out.println("Patient Disease: " + parts[3]);

                    int age = Integer.parseInt(parts[2]);
                    String disease = parts[3];
                    
                    if(age > 60 && disease.toLowerCase() == "heart disease") {
                        System.out.println("Priority Patient – Immediate Attention Required");
                    }
                    return;
                }
            }
            throw new PatientNotFoundException("Patient with ID " + id + " not found in file");
        }
    }

    public static void displayPatients() throws IOException {
        String filePath = "com/hospital/PatientFile.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 4);
                System.out.println("Patient Id: " + parts[0]);
                System.out.println("Patient Name: " + parts[1]);
                System.out.println("Patient Age: " + parts[2]);
                System.out.println("Patient Disease: " + parts[3]);
            }
        }
    }
}
