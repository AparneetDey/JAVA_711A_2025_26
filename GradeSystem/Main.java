package GradeSystem;

import java.util.Scanner;

// Design a Java program that evaluates a student's marks and assigns a grade using proper exception handling. The program should ask the user to enter the student's name and marks (out of 100). Based on the marks entered, the program should calculate the grade using the following criteria:

// Marks ≥ 90 → Grade A

// Marks ≥ 75 → Grade B

// Marks ≥ 60 → Grade C

// Marks ≥ 40 → Grade D

// Marks < 40 → Fail

// The program must use exception handling to ensure safe execution. If the user enters marks less than 0 or greater than 100, the program should throw and handle an IllegalArgumentException with an appropriate message such as "Marks must be between 0 and 100." If the user enters invalid input such as text instead of numbers, the program should handle the InputMismatchException and display "Invalid input. Please enter numeric marks only."

// All input and grade calculation logic must be written inside the try block, exception handling must be done using appropriate catch blocks, and a finally block must always execute and display a message such as "Marks evaluation completed."

// Additionally, you should display the following details if no exception occurs:

// Student Name

// Marks Entered

// Grade Obtained

class Student {
    String name;
    static int subjects = 5;
    int[] marks = new int[subjects];
    int totalMarks = 0;
    char grade;

    public Student(String name) {
        this.name = name;
    }

    public void getMarks() {
        try {
            int mark;
            Scanner sc = new Scanner(System.in);
            for(int i=0; i<subjects; i++) {
                System.out.print("Subject "+(i+1)+": ");
                mark = sc.nextInt();
                if(mark < 0 || mark > 100) {
                    throw new IllegalArgumentException("Marks must be between 0 and 100");
                }
                marks[i] = mark;
            }
            calculateTotalMarks();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void calculateTotalMarks() {
        for(int i=0; i<subjects; i++) {
            totalMarks += marks[i];
        }
        getGrade();
    }

    private void getGrade() {
        try {
            int avg = totalMarks / 5;
            if(avg>=90) {
                grade = 'A';
            } else if(avg>=75) {
                grade = 'B';
            } else if(avg>=60) {
                grade = 'C';
            } else if(avg>=40) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            display();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Marks Evaluation Completed");
        }
    }

    private void display() {
        System.out.println("Student Name: " + name);
        System.out.print("Marks Entered: ");
        for(int i=0; i<subjects; i++) {
            System.out.print(marks[i] + " ");
        }
        System.out.println();
        System.out.println("Total marks: " + totalMarks);
        System.out.println("Grade: " + grade);
    }
}

public class Main {
    public static void main(String args[]) {
        Student s = new Student("Ram");
        s.getMarks();
    } 
}
