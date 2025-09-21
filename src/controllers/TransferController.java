package controllers;

import abstracts.Transfer;
import models.Deposit;
import models.Withdrawal;
import utils.DatabaseConnection;
import controllers.AccountController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class TransferController {
    private Transfer withdrawal;
    private Transfer deposit;

    public TransferController() {}

    public boolean withdrawal(String accountCode, BigDecimal amount, Timestamp transaction_time) {
        this.withdrawal = new Withdrawal(accountCode, amount, transaction_time);

        String insertSql = "INSERT INTO Withdrawals (account_code, amount, transaction_time) VALUES (?, ?, ?)";
        String updateSql = "UPDATE Accounts SET balance = balance - ? WHERE account_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            insertStmt.setString(1, accountCode);
            insertStmt.setBigDecimal(2, amount);
            insertStmt.setTimestamp(3, transaction_time);
            int rows = insertStmt.executeUpdate();

            if (rows > 0) {
                updateStmt.setBigDecimal(1, amount);
                updateStmt.setString(2, accountCode);
                int updatedRows = updateStmt.executeUpdate();
                return updatedRows > 0;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deposit(String accountCode, BigDecimal amount, Timestamp transaction_time) {
        this.deposit = new Deposit(accountCode, amount, transaction_time);

        String insertSql = "INSERT INTO Deposits (account_code, amount, transaction_time) VALUES (?, ?, ?)";
        String updateSql = "UPDATE Accounts SET balance = balance + ? WHERE account_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            insertStmt.setString(1, accountCode);
            insertStmt.setBigDecimal(2, amount);
            insertStmt.setTimestamp(3, transaction_time);
            int rows = insertStmt.executeUpdate();

            if (rows > 0) {
                updateStmt.setBigDecimal(1, amount);
                updateStmt.setString(2, accountCode);
                int updatedRows = updateStmt.executeUpdate();
                return updatedRows > 0;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
    public boolean transfer(String fromAccount, String toAccount, BigDecimal amount, Timestamp transaction_time) {
        // Step 1: Withdraw from source account
        boolean withdrawalSuccess = this.withdrawal(fromAccount, amount, transaction_time);

        if (withdrawalSuccess) {
            // Step 2: Deposit into destination account
            boolean depositSuccess = this.deposit(toAccount, amount, transaction_time);

            if (depositSuccess) {
                return true;
            } else {
                System.out.println("Deposit failed after withdrawal. Manual correction may be needed.");
                return false;
            }
        } else {
            System.out.println("Withdrawal failed. Transfer aborted.");
            return false;
        }
    }

}
