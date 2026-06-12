import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystem {

    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    // Display All Students
    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n----- Student List -----");

        for (Student student : students) {
            System.out.println(student);
            System.out.println("----------------------");
        }
    }

    // Save students to file
    public void saveToFile() {

        File file = new File("students.txt");

        System.out.println("Saving data to: " + file.getAbsolutePath());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Student student : students) {

                writer.write(student.getName() + ","
                        + student.getRollNumber() + ","
                        + student.getGrade());

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving student data.");
            e.printStackTrace();
        }
    }

    // Load students from file
    public void loadFromFile() {

        File file = new File("students.txt");

        if (!file.exists()) {
            System.out.println("No previous student data found.");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    String name = data[0];
                    int rollNumber = Integer.parseInt(data[1]);
                    String grade = data[2];

                    students.add(new Student(name, rollNumber, grade));
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error loading student data.");
            e.printStackTrace();
        }
    }

    // Search Student
    public Student searchStudent(int rollNumber) {

        for (Student student : students) {

            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }

        return null;
    }

    // Remove Student
    public boolean removeStudent(int rollNumber) {

        Student student = searchStudent(rollNumber);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    // Update Student
    public boolean updateStudent(int rollNumber, String newName, String newGrade) {

        Student student = searchStudent(rollNumber);

        if (student != null) {

            student.setName(newName);
            student.setGrade(newGrade);

            return true;
        }

        return false;
    }
}
