package main;

import controllers.UserController;
import controllers.AccountController;
import abstracts.Account;
import java.util.Scanner;
import java.util.List;
import models.User;
public class Main {
    public static void main(String[] args) {
        // Step 1: Set up scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Step 2: Create controllers
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

            // Get user choice
            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= 3) {
                        validInput = true;
                    } else {
                        System.out.print("Invalid choice. Please enter 1, 2, or 3: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number (1-3): ");
                }
            }

            // Handle exit
            if (choice == 3) {
                System.out.println("Goodbye!");
                scanner.close();
                return;
            }

            // Get username and password
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Handle signup
            if (choice == 1) {
                if (userController.signUp(username, password)) {
                    System.out.println("Signup successful! You are logged in.");
                    loggedIn = true;
                } else {
                    System.out.println("Signup failed. Try a different username.");
                }
            }
            // Handle login
            else if (choice == 2) {
                if (userController.login(username, password)) {
                    System.out.println("Login successful! Welcome, " + username);
                    loggedIn = true;
                } else {
                    System.out.println("Login failed. Check username or password.");
                }
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

            // Get user choice
            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= 4) {
                        validInput = true;
                    } else {
                        System.out.print("Invalid choice. Please enter 1, 2, 3, or 4: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number (1-4): ");
                }
            }

            // Handle logout
            if (choice == 4) {
                User.setCurrentUser(0);
                System.out.println("Logout successful.");
                loggedIn = false;
                continue;
            }

            // Handle create checking account
            if (choice == 1) {
                System.out.print("Enter starting balance (e.g., 1000): ");
                double balance = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        balance = Double.parseDouble(scanner.nextLine());
                        if (balance >= 0) {
                            validInput = true;
                        } else {
                            System.out.print("Balance must be 0 or more: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a number: ");
                    }
                }
                System.out.print("Enter negative limit (e.g., 900): ");
                double negativeLimit = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        negativeLimit = Double.parseDouble(scanner.nextLine());
                        if (negativeLimit >= 0) {
                            validInput = true;
                        } else {
                            System.out.print("Negative limit must be 0 or more: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a number: ");
                    }
                }
                if (accountController.createCheckingAccount(balance, negativeLimit)) {
                    System.out.println("Account created successfully!");
                } else {
                    System.out.println("Failed to create checking account.");
                }
            }
            // Handle create saving account
            else if (choice == 2) {
                System.out.print("Enter starting balance (e.g., 1000): ");
                double balance = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        balance = Double.parseDouble(scanner.nextLine());
                        if (balance >= 0) {
                            validInput = true;
                        } else {
                            System.out.print("Balance must be 0 or more: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a number: ");
                    }
                }
                System.out.print("Enter interest rate (e.g., 2.5): ");
                double interestRate = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        interestRate = Double.parseDouble(scanner.nextLine());
                        if (interestRate >= 0) {
                            validInput = true;
                        } else {
                            System.out.print("Interest rate must be 0 or more: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a number: ");
                    }
                }
                if (accountController.createSavingAccount(balance, interestRate)) {
                    System.out.println("Account created successfully!");
                } else {
                    System.out.println("Failed to create saving account.");
                }
            }
            // Handle list accounts
            else if (choice == 3) {
                List<Account> accounts = accountController.getUserAccounts(User.getCurrentUser());
                if (accounts.isEmpty()) {
                    System.out.println("No accounts found.");
                } else {
                    System.out.println("Your accounts:");
                    for (Account account : accounts) {
                        account.showDetails();
                    }
                }
            }
        }

        // Step 5: Close scanner
        scanner.close();
    }
}