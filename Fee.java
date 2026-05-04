package finance;

public class Fee {
    private double totalFee;
    private double paidAmount;

    public Fee(double totalFee) {
        this.totalFee = totalFee;
    }

    public void pay(double amount) {
        paidAmount += amount;
    }

    public void showStatus() {
        System.out.println("Remaining Fee: " + (totalFee - paidAmount));
    }
}
