package users;

import java.util.ArrayList;
import java.util.List;
import university.Course;

public class Student extends Person {
    private String studentID;
    private double gpa;
    private List<Course> enrolledCourses;

    public Student(String name, int age, String cnic, String studentID) {
        super(name, age, cnic);
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>();
    }

    public void registerCourse(Course c) {
        enrolledCourses.add(c);
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + " | ID: " + studentID + " | GPA: " + gpa);
    }
}
