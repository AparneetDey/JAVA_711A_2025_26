package Expriment1;
public class Student {
    String university = "CU";
    int uid;
    String name;
    int marks;

    public Student() {
        uid = 0;
        name = "No Name";
        marks = 0;
    }

    public Student(int uid, String name, int marks) {
        this.uid = uid;
        this.name = name;
        this.marks = marks;
    }

    public static void main(String args[]) {
        Student S1 = new Student();
        Student S2 = new Student(101, "Aryan", 40);
        System.out.println("S1:");
        System.out.println(S1.university + " " + S1.uid + " " + S1.name + " " + S1.marks);
        System.out.println("S2:");
        System.out.println(S2.university + " " + S2.uid + " " + S2.name + " " + S2.marks);
    }
}