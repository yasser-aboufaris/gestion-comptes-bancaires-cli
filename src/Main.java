package main;

import controllers.UserController;
import controllers.AccountController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Step 1: Set up scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Step 2: Create controllers (not used yet)
        UserController userController = new UserController();
        AccountController accountController = new AccountController();

        // Step 3: Authentication menu loop (signup, login, exit)
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1 = Sign Up");
            System.out.println("2 = Login");
            System.out.println("3 = Exit");
            System.out.print("Choose action (1-3): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (userController.signUp(username, password)) {
                    System.out.println("Signup successful. Please log in.");
                } else {
                    System.out.println("Signup failed. Try again.");
                }
            }
            else if (choice.equals("2")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (userController.login(username, password)) {
                    System.out.println("Login successful. Welcome " + username + "!");
                    loggedIn = true;
                } else {
                    System.out.println("Invalid credentials. Try again.");
                }
            }
            else if (choice.equals("3")) {
                System.out.println("Goodbye!");
                scanner.close();
                return;
            }
            else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }


        // Step 4: Account menu loop (create accounts, list accounts, logout)
        while (loggedIn) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1 = Create Checking Account");
            System.out.println("2 = Create Saving Account");
            System.out.println("3 = List Accounts");
            System.out.println("4 = Logout");
            System.out.print("Choose action (1-4): ");

            // Get user choice (placeholder)
            String choice = scanner.nextLine();

            // Handle create checking account
            if (choice.equals("1")) {
                System.out.print("Enter account code (e.g., CHK001): ");
                String accountCode = scanner.nextLine();
                System.out.print("Enter starting balance (e.g., 1000): ");
                String balance = scanner.nextLine();
                System.out.print("Enter negative limit (e.g., 900): ");
                String negativeLimit = scanner.nextLine();
                // Placeholder: Call accountController.createAccount(accountCode, "CHECKING", balance, negativeLimit)
                System.out.println("Create checking account attempt");
            }
            // Handle create saving account
            else if (choice.equals("2")) {
                System.out.print("Enter account code (e.g., SAV001): ");
                String accountCode = scanner.nextLine();
                System.out.print("Enter starting balance (e.g., 1000): ");
                String balance = scanner.nextLine();
                System.out.print("Enter interest rate (e.g., 2.5): ");
                String interestRate = scanner.nextLine();
                // Placeholder: Call accountController.createAccount(accountCode, "SAVING", balance, interestRate)
                System.out.println("Create saving account attempt");
            }
            // Handle list accounts
            else if (choice.equals("3")) {
                // Placeholder: Call accountController.getUserAccounts()
                System.out.println("List accounts attempt");
            }
            // Handle logout
            else if (choice.equals("4")) {
                // Placeholder: Reset logged-in user state
                System.out.println("Logout attempt");
                loggedIn = false;
            }
            else {
                System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }

        // Step 5: Close scanner
        scanner.close();
    }
}