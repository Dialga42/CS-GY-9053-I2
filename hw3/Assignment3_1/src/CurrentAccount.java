public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String x, double y) {
        super(x);
        overdraftLimit = y;
    }

    public boolean withdraw(double val) {
        if (val < 0 || balance - val + overdraftLimit < 0)
            return false;
        balance -= val;
        return true;
    }

    public void bankUpdate() {
        if (balance < 0)
            System.out.println("Account " + getAccountNumber() + "overdraft $" + (-balance));
    }
}
