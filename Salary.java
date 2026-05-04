package finance;

public class Salary {
    private double baseSalary;
    private double tax;

    public Salary(double baseSalary, double tax) {
        this.baseSalary = baseSalary;
        this.tax = tax;
    }

    public double calculateSalary() {
        return baseSalary - tax;
    }
}
