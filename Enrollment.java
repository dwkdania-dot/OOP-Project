public class Enrollment {
    private Student student; //Module 1
    private Course course;   //Module 2
    private String enrollmentDate;

    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
}
