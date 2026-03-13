package com.hospital.patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.InvalidAgeException;

public class Patient {
    int patientId;
    String name;
    int age;
    String disease;

    public Patient(int id, String name, int age, String disease) throws InvalidAgeException, DuplicatePatientException, IOException {
        String filePath = "com/hospital/PatientFile.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(id + " ") || line.equals(String.valueOf(id))) {
                    throw new DuplicatePatientException("Patient id already exist");
                }
            }
        }

        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age should be positive and less than 120");
        }

        this.patientId = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getter() {
        return this.patientId + " " + this.name + " " + this.age + " " + this.disease;
    }
}
