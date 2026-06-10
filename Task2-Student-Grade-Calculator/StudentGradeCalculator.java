import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== Welcome to Student Grade Calculator =====");
        System.out.print("Enter Total Maximum Marks: ");
        int MM = sc.nextInt();

        System.out.println("\n\t\tEnter the marks of the subjects:");
        System.out.print("\t\t\tMaths: ");
        float maths = sc.nextFloat();
        System.out.print("\t\t\tCOA: ");
        float COA = sc.nextFloat();
        System.out.print("\t\t\tComputer Networks: ");
        float CN = sc.nextFloat();
        System.out.print("\t\t\tDBMS: ");
        float dbms = sc.nextFloat();
        System.out.print("\t\t\tOperating System: ");
        float OS = sc.nextFloat();

        // Calculate total marks
        float totalMarks = maths + COA + COA + CN + dbms + OS;
        // Calculate percentage
        float percentage = (totalMarks / MM) * 100;
        System.out.println("\n===== RESULT =====");
        System.out.println("Total Marks = " + totalMarks + " out of " + MM);
        System.out.printf("Percentage = %.2f%%\n", percentage);

        // Grade Calculation
        if (percentage >= 90) {
            System.out.println("Grade = A+");
        }
        else if (percentage >= 80) {
            System.out.println("Grade = A");
        }
        else if (percentage >= 70) {
            System.out.println("Grade = B");
        }
        else if (percentage >= 60) {
            System.out.println("Grade = C");
        }
        else if (percentage >= 50) {
            System.out.println("Grade = D");
        }
        else {
            System.out.println("Grade = F");
        }
        sc.close();
    }
}
