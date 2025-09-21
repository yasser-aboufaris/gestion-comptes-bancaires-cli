package models;
import abstracts.Account;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    private BigDecimal negativeLimit = new BigDecimal("900.0");

    public CheckingAccount(String accountCode, int ownerId, BigDecimal balance) {
        super(accountCode, ownerId, balance);
    }

    public BigDecimal getNegativeLimit() {
        return negativeLimit;
    }

    public void setNegativeLimit(BigDecimal negativeLimit) {
        this.negativeLimit = negativeLimit;
    }


    @Override
    public void showDetails() {
        System.out.println("=== Checking Account ===");
        System.out.println("Account Code: " + getAccountCode());
        System.out.println("Owner ID: " + getOwnerId());
        System.out.println("Balance: $" + getBalance());
        System.out.println("Negative Limit: $" + negativeLimit);
    }
}