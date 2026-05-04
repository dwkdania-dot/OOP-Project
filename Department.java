package university;

import java.util.ArrayList;
import java.util.List;
import users.Teacher;

public class Department {
    private String departmentName;
    private List<Course> courses;
    private Teacher teacher;

    public Department(String name) {
        this.departmentName = name;
        courses = new ArrayList<>();
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    public void assignTeacher(Teacher t) {
        this.teacher = t;
    }

    public void displayDepartment() {
        System.out.println("Department: " + departmentName);
        if (teacher != null)
            teacher.displayInfo();

        for (Course c : courses)
            c.displayCourse();
    }
}
