package controllers;

import models.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class UserController {

    public UserController() {}

    public boolean signUp(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    System.out.println("User registered with id=" + userId);
                    User.setCurrentUser(userId);
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    User.setCurrentUser(userId);
                    System.out.println("Login successful. User id=" + userId);
                    return true;
                }
            }
            return false; // no match
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return false;
        }
    }
}