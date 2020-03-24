import java.util.*;

public class Bank {
    List<BankAccount> bankAccountList;

    public Bank() {
        bankAccountList = new ArrayList<>();
    }

    public void update() {
        for (BankAccount i : bankAccountList) {
            i.bankUpdate();
        }
    }

    public boolean openAccount(String name, int type, double val) {
        for (BankAccount i : bankAccountList) {
            if (i.getAccountNumber().equals(name)) {
                return false;
            }
        }
        if (type == 1) {
            bankAccountList.add(new BankAccount(name));
        } else if (type == 2) {
            bankAccountList.add(new SavingsAccount(name, val));
        } else if (type == 3) {
            bankAccountList.add(new CurrentAccount(name, val));
        } else {
            return false;
        }
        return true;
    }

    public boolean closeAccount(String name) {
        for (BankAccount i : bankAccountList) {
            if (i.getAccountNumber().equals(name)) {
                bankAccountList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void payDividend(double val) {
        for (BankAccount i : bankAccountList) {
            i.balance += val;
        }
    }
}
