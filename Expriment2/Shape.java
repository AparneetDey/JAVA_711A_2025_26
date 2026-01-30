package Expriment2;
public class Shape {
    public static void Area(int S) {
        int area = S*S;
        System.out.println("Squre Area: " + area);
    }
    public static void Area(int l, int b) {
        int area = l*b;
        System.out.println("Squre Area: " + area);
    }
    public static void Area(double r) {
        double area = 3.14*r*r;
        System.out.println("Squre Area: " + area);
    }

    public static void main(String args[]) {
        Area(5);
        Area(2,3);
        Area(2.1);
    }
}
