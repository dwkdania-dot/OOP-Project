public class Result {
    private Enrollment enrollment; 
    private double marks;
    private String grade;
    private double gpa;

    public Result(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public void calculateGrade(double marks) {
        this.marks = marks;
        if (marks >= 85) {
            this.grade = "A";
            this.gpa = 4.0;
        } else if (marks >= 75) {
            this.grade = "B";
            this.gpa = 3.5;
        } else if (marks >= 65) {
            this.grade = "C";
            this.gpa = 3.0;
        } else {
            this.grade = "F";
            this.gpa = 0.0;
        }
    }

    public double getGpa() { return gpa; }

    public void displayResult() {
        System.out.println("Student: " + enrollment.getStudent().getName());
        System.out.println("Course: " + enrollment.getCourse().getCourseName());
        System.out.println("Grade: " + grade + " | GPA: " + gpa);
    }
}

