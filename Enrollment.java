package academic;

import users.Student;
import university.Course;

public class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student s, Course c) {
        this.student = s;
        this.course = c;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void showEnrollment() {
        System.out.println(student.getName() + " enrolled in " + course.getCourseCode());
    }
}
