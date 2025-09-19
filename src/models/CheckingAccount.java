package models;
import abstracts.Account;
public class CheckingAccount extends Account {
    private double negativeLimit = 900.0; // Default negative limit

    public CheckingAccount(String accountCode, int ownerId, double balance) {
        super(accountCode, ownerId, balance);
    }

    public double getNegativeLimit() {
        return negativeLimit;
    }

    // Possibly a duplicate getNegativeLimit() causing the error
    // public double getNegativeLimit() { ... }

    @Override
    public void showDetails() {
        System.out.println("=== Checking Account ===");
        System.out.println("Account Code: " + getAccountCode());
        System.out.println("Owner ID: " + getOwnerId());
        System.out.println("Balance: $" + getBalance());
        System.out.println("Negative Limit: $" + negativeLimit);
    }
}