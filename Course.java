package university;

public class Course {
    private String courseCode;
    private int creditHours;

    public Course(String courseCode, int creditHours) {
        this.courseCode = courseCode;
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void displayCourse() {
        System.out.println("Course: " + courseCode + " | Credit Hours: " + creditHours);
    }
}
