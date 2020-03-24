public class SavingsAccount extends BankAccount {
    private double interest;

    public SavingsAccount(String x, double y) {
        super(x);
        interest = y;
    }

    public boolean addInterest() {
        balance += interest;
        return true;
    }

    public void bankUpdate() {
        addInterest();
    }
}
