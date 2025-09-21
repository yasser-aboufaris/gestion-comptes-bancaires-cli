package controllers;

import abstracts.Transfer;
import models.Deposit;
import models.Withdrawal;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransferController {
    private Transfer withdrawal;
    private Transfer deposit;

    public TransferController() {}

    public void withdrawal(String accountCode, BigDecimal amount, Timestamp transaction_time) {
        this.withdrawal = new Withdrawal(accountCode, amount, transaction_time);
        String sql = "INSERT INTO Withdrawals (account_code, amount, transaction_time) VALUES (?, ?, ?)";
    }

    public void deposit(String accountCode, BigDecimal amount, Timestamp transaction_time) {
        this.deposit = new Deposit(accountCode, amount, transaction_time);
        String sql = "INSERT INTO Deposits (account_code, amount, transaction_time) VALUES (?, ?, ?)";
    }
}
