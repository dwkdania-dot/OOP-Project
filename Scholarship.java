package finance;

import users.Student;

public class Scholarship {
    private Student student;
    private double discount;

    public Scholarship(Student s) {
        this.student = s;
    }

    public void apply() {
        if (student.getGpa() >= 3.5)
            discount = 20;
        else
            discount = 0;
    }

    public void showScholarship() {
        System.out.println("Scholarship: " + discount + "%");
    }
}
