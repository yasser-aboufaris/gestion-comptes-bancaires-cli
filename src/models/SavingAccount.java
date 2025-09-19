package models;
import abstracts.Account;

public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount(String accountCode, int ownerId, double balance, double interestRate) {
        super(accountCode, ownerId, balance); // call parent constructor
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void showDetails() {
        System.out.println("=== Saving Account ===");
        System.out.println("Account Code: " + getAccountCode());
        System.out.println("Owner ID: " + getOwnerId());
        System.out.println("Balance: " + getBalance());
        System.out.println("Interest Rate: " + interestRate + "%");
    }


}