package controllers;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import abstracts.Account;
import models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;

import utils.DatabaseConnection;
import models.CheckingAccount;
import models.SavingAccount;
public class AccountController{
    public AccountController(){}
    public int CurrentUserId = User.getCurrentUser();
    public boolean createAccount(String accountCode, String accountType, double initialBalance, double extraParam) {
       if (!User.isLoggedIn()) {
            System.out.println("Please log in first.");
            return false;
        }

        String sql = "INSERT INTO accounts (account_code, owner_id, balance, account_type, negative_limit, interest_rate) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, accountCode);
            stmt.setInt(2, User.getCurrentUser());
            stmt.setDouble(3, initialBalance);
            stmt.setString(4, accountType);

            if (accountType.equalsIgnoreCase("CHECKING")) {
                stmt.setDouble(5, extraParam); // negative_limit
                stmt.setNull(6, Types.DOUBLE); // No interest rate
            } else if (accountType.equalsIgnoreCase("SAVING")) {
                stmt.setNull(5, Types.DOUBLE); // No negative limit
                stmt.setDouble(6, extraParam); // interest_rate
            } else {
                System.out.println("Invalid account type. Use CHECKING or SAVING.");
                conn.close();
                return false;
            }

            int rows = stmt.executeUpdate();
            conn.close();

            if (rows > 0) {
                System.out.println("Created " + accountType + " account: " + accountCode);
                return true;
            } else {
                System.out.println("Could not create account.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Database problem: " + e.getMessage());
            return false;
        }
    }

}