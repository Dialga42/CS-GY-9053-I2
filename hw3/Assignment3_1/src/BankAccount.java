public class BankAccount {
    private String accountNumber;
    public double balance;

    public BankAccount() {
    }

    public BankAccount(String x) {
        balance = 0;
        accountNumber = x;
    }

    public boolean deposit(double val) {
        if (val < 0)
            return false;
        balance += val;
        return true;
    }

    public boolean withdraw(double val) {
        if (val < 0)
            return false;
        balance -= val;
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return BankAccount.class.getName() + " " + "accountNumber=" + accountNumber + " balance=" + balance;
    }

    public void bankUpdate() {
    }
}