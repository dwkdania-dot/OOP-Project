package university;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String uniName;
    private List<Department> departments;

    public University(String name) {
        this.uniName = name;
        departments = new ArrayList<>();
    }

    public void addDepartment(Department d) {
        departments.add(d);
    }

    public void displayUniversity() {
        System.out.println("University: " + uniName);
        for (Department d : departments)
            d.displayDepartment();
    }
}
