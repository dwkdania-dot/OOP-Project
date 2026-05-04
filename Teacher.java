package users;

public class Teacher extends Person {
    private String designation;
    private String subject;

    public Teacher(String name, int age, String cnic, String designation, String subject) {
        super(name, age, cnic);
        this.designation = designation;
        this.subject = subject;
    }

    @Override
    public void displayInfo() {
        System.out.println("Teacher: " + name + " | " + designation + " | Subject: " + subject);
    }
}
