package com.hospital.exception;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException() {
        super();
    }
    public PatientNotFoundException(String message) {
        super(message);
    }
}
