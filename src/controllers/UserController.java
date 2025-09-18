package controllers;

import models.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class UserController {

    public UserController() {}


    public boolean signUp(User user) {
        final String sql = "INSERT INTO users (username, password) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getInt("id")); // set generated id back on the model
                    System.out.println("User registered with id=" + user.getId());
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    public User login(String username, String password) {
        final String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password")); // fine for practice
                    return user;
                }
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }

    public User login(User creds) {
        return login(creds.getUsername(), creds.getPassword());
    }



    // MAIN METHOD FOR TESTING
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController controller = new UserController();

        System.out.println("Choose action: 1 = SignUp, 2 = Login");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (choice == 1) {
            User newUser = new User(username, password);
            if (controller.signUp(newUser)) {
                System.out.println("Signup successful!");
            } else {
                System.out.println("Signup failed.");
            }
        } else if (choice == 2) {
            User user = controller.login(username, password);
            if (user != null) {
                System.out.println("Login successful! Welcome, " + user.getUsername());
            } else {
                System.out.println("Login failed.");
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
