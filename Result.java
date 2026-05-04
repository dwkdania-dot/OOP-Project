package academic;

public class Result {
    private Enrollment enrollment;
    private double marks;
    private char grade;
    private double gpa;

    public Result(Enrollment e) {
        this.enrollment = e;
    }

    public void calculateResult(double marks) {
        this.marks = marks;

        if (marks >= 85) {
            grade = 'A';
            gpa = 4.0;
        } else if (marks >= 75) {
            grade = 'B';
            gpa = 3.5;
        } else if (marks >= 65) {
            grade = 'C';
            gpa = 3.0;
        } else {
            grade = 'F';
            gpa = 0.0;
        }
    }

    public double getGpa() {
        return gpa;
    }

    public void displayResult() {
        System.out.println("Grade: " + grade + " | GPA: " + gpa);
    }
}
