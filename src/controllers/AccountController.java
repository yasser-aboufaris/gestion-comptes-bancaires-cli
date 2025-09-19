package controllers;

import models.SavingAccount;
import java.sql.*;
import java.math.BigDecimal;
import abstracts.Account;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;
import models.User;
import models.CheckingAccount;

public class AccountController {

    public AccountController() {}

    public boolean createCheckingAccount(double initialBalance, double negativeLimit) {
        if (!User.isLoggedIn()) {
            System.out.println("Error: Please log in first.");
            return false;
        }

        // Generate unique account code
        String accountCode = generateAccountCode("CHECKING");
        if (accountCode == null) {
            System.out.println("Error: Could not generate account code.");
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

    public boolean createSavingAccount(double initialBalance, double interestRate) {
        if (!User.isLoggedIn()) {
            System.out.println("Error: Please log in first.");
            return false;
        }

        String accountCode = generateAccountCode("SAVING");
        if (accountCode == null) {
            System.out.println("Error: Could not generate account code.");
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

    public List<Account> getUserAccounts(int userId) {
        if (!User.isLoggedIn() || userId != User.getCurrentUser()) {
            System.out.println("Error: Please log in.");
            return new ArrayList<>();
        }

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT account_code, owner_id, balance, account_type FROM accounts WHERE owner_id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String accountCode = rs.getString("account_code");
                int ownerId = rs.getInt("owner_id");
                double balance = rs.getDouble("balance");
                String accountType = rs.getString("account_type");

                if (accountType.equalsIgnoreCase("CHECKING")) {
                    accounts.add(new CheckingAccount(accountCode, ownerId, balance));
                } else if (accountType.equalsIgnoreCase("SAVING")) {
                    accounts.add(new SavingAccount(accountCode, ownerId, balance, 0.0));
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: Database issue - " + e.getMessage());
            return new ArrayList<>();
        }

        return accounts;
    }

    public String generateAccountCode(String accountType) {
        String prefix = accountType.equalsIgnoreCase("CHECKING") ? "CHK" : "SAV";

        String sql = "SELECT COUNT(*) FROM accounts WHERE account_code LIKE ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, prefix + "%");
            ResultSet rs = stmt.executeQuery();

            // Step 4: Get next number
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1) + 1;
            }

            conn.close();

            return String.format("%s%03d", prefix, count);
        } catch (SQLException e) {
            System.out.println("Error: Database issue - " + e.getMessage());
            return null;
        }
    }
}