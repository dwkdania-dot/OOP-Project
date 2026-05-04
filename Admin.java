package users;

public class Admin extends Person {
    private String employeeID;

    public Admin(String name, int age, String cnic, String employeeID) {
        super(name, age, cnic);
        this.employeeID = employeeID;
    }

    public void generateReport() {
        System.out.println("Admin generating report...");
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name + " | ID: " + employeeID);
    }
}
