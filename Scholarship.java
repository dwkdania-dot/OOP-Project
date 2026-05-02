public class Scholarship {
    private Student student;
    private Result result;
    private double discountPercentage;

    public Scholarship(Student student, Result result) {
        this.student = student;
        this.result = result;
    }

    public void applyScholarship() {
        if (result.getGpa() >= 3.5) {
            this.discountPercentage = 20.0;
        } else {
            this.discountPercentage = 0.0;
        }
    }

    public void displayScholarshipDetails() {
        System.out.println("Scholarship Status for " + student.getName());
        if (discountPercentage > 0) {
            System.out.println("Status: Approved (" + discountPercentage + "% Discount)");
        } else {
            System.out.println("Status: Not Eligible (GPA below 3.5)");
        }
    }
}

