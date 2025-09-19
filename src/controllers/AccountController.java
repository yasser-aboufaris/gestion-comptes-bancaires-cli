package controllers;

import java.sql.*;
import java.math.BigDecimal;
import utils.DatabaseConnection;
import models.User;

public class AccountController {

    public AccountController() {}

    public boolean createCheckingAccount(String accountCode, double initialBalance, double negativeLimit) {
        if (!User.isLoggedIn()) {
            System.out.println("Error: Please log in first.");
            return false;
        }

        String sql = "INSERT INTO accounts (account_code, owner_id, balance, account_type, negative_limit, interest_rate) VALUES (?, ?, ?, 'CHECKING', ?, NULL)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, accountCode);
            stmt.setInt(2, User.getCurrentUser());
            stmt.setBigDecimal(3, BigDecimal.valueOf(initialBalance));
            stmt.setBigDecimal(4, BigDecimal.valueOf(negativeLimit));

            int rows = stmt.executeUpdate();
            conn.close();

            if (rows > 0) {
                System.out.println("Created CHECKING account: " + accountCode);
                return true;
            } else {
                System.out.println("Error: Could not create checking account.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: Database issue - " + e.getMessage());
            return false;
        }
    }

    public boolean createSavingAccount(String accountCode, double initialBalance, double interestRate) {
        if (!User.isLoggedIn()) {
            System.out.println("Error: Please log in first.");
            return false;
        }

        String sql = "INSERT INTO accounts (account_code, owner_id, balance, account_type, negative_limit, interest_rate) VALUES (?, ?, ?, 'SAVING', NULL, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, accountCode);
            stmt.setInt(2, User.getCurrentUser());
            stmt.setBigDecimal(3, BigDecimal.valueOf(initialBalance));
            stmt.setBigDecimal(4, BigDecimal.valueOf(interestRate));

            int rows = stmt.executeUpdate();
            conn.close();

            if (rows > 0) {
                System.out.println("Created SAVING account: " + accountCode);
                return true;
            } else {
                System.out.println("Error: Could not create saving account.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: Database issue - " + e.getMessage());
            return false;
        }
    }
}