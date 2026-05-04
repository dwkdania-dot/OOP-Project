import university.*;
import users.*;
import academic.*;
import finance.*;
import resource.*;

public class MainSystem {
    public static void main(String[] args) {

        University uni = new University("Riphah International University");

        Department cs = new Department("Computer Science");
        Course oop = new Course("CS101", 3);

        Teacher t = new Teacher("Dr Sara", 40, "12345", "Professor", "OOP");

        cs.addCourse(oop);
        cs.assignTeacher(t);

        uni.addDepartment(cs);

        Student s = new Student("Ali", 20, "67890", "S101");

        Enrollment e = new Enrollment(s, oop);
        Result r = new Result(e);
        r.calculateResult(88);

        s.setGpa(r.getGpa());

        Scholarship sch = new Scholarship(s);
        sch.apply();

        Fee f = new Fee(50000);
        f.pay(20000);

        Resource lab = new Resource("Computers", 25);

        // OUTPUT
        uni.displayUniversity();
        e.showEnrollment();
        r.displayResult();
        sch.showScholarship();
        f.showStatus();
        lab.display();
    }
}
