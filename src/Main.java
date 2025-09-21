package main;

import controllers.UserController;
import controllers.AccountController;
import controllers.TransferController;
import abstracts.Account;
import java.util.Scanner;
import java.util.List;
import models.User;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        // Step 1: Set up scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Step 2: Create controllers
        UserController userController = new UserController();
        AccountController accountController = new AccountController();
        TransferController transferController = new TransferController();

        // Step 3: Authentication menu loop (signup, login, exit)
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1 = Sign Up");
            System.out.println("2 = Login");
            System.out.println("3 = Exit");
            System.out.print("Choose action (1-3): ");

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

        // Step 4: Account menu loop (create accounts, list accounts, make transaction, logout)
        while (loggedIn) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1 = Create Checking Account");
            System.out.println("2 = Create Saving Account");
            System.out.println("3 = List Accounts");
            System.out.println("4 = Make Transaction");
            System.out.println("5 = Logout");
            System.out.print("Choose action (1-5): ");

            // Get user choice
            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= 5) {
                        validInput = true;
                    } else {
                        System.out.print("Invalid choice. Please enter 1, 2, 3, 4, or 5: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Please enter a number (1-5): ");
                }
            }

            // Handle logout
            if (choice == 5) {
                User.setCurrentUser(0);
                System.out.println("Logout successful.");
                loggedIn = false;
                continue;
            }

            // Handle create checking account
            if (choice == 1) {
                System.out.print("Enter starting balance (e.g., 1000): ");
                BigDecimal balance = null;
                validInput = false;
                while (!validInput) {
                    try {
                        balance = new BigDecimal(scanner.nextLine());
                        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
                            validInput = true;
                        } else {
                            System.out.print("Balance must be 0 or more: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Please enter a number: ");
                    }
                }
                System.out.print("Enter negative limit (e.g., 900): ");
                BigDecimal negativeLimit = null;
                validInput = false;
                while (!validInput) {
                    try {
                        negativeLimit = new BigDecimal(scanner.nextLine());
                        if (negativeLimit.compareTo(BigDecimal.ZERO) >= 0) {
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
            else if (choice == 2) {
                System.out.print("Enter starting balance (e.g., 1000): ");
                BigDecimal balance = null;
                validInput = false;
                while (!validInput) {
                    try {
                        balance = new BigDecimal(scanner.nextLine());
                        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
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
            else if (choice == 3) {
                List<Account> accounts = accountController.getUserAccounts();
                if (accounts.isEmpty()) {
                    System.out.println("No accounts found.");
                } else {
                    System.out.println("Your accounts:");
                    for (Account account : accounts) {
                        account.showDetails();
                    }
                }
            }
            else if (choice == 4) {
                boolean inTransactionMenu = true;
                while (inTransactionMenu) {
                    System.out.println("\n=== Transaction Menu ===");
                    System.out.println("1 = Withdrawal");
                    System.out.println("2 = Deposit");
                    System.out.println("3 = Transfer");
                    System.out.println("4 = Exit");
                    System.out.print("Choose action (1-4): ");

                    int tChoice = 0;
                    boolean validTInput = false;
                    while (!validTInput) {
                        try {
                            tChoice = Integer.parseInt(scanner.nextLine());
                            if (tChoice >= 1 && tChoice <= 4) {
                                validTInput = true;
                            } else {
                                System.out.print("Invalid choice. Please enter 1, 2, 3, or 4: ");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid input. Please enter a number (1-4): ");
                        }
                    }

                    if (tChoice == 4) {
                        System.out.println("Exiting transaction menu.");
                        inTransactionMenu = false;
                        continue;
                    }

                    System.out.print("Enter account code: ");
                    String accountCode = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    BigDecimal amount = null;
                    validTInput = false;
                    while (!validTInput) {
                        try {
                            amount = new BigDecimal(scanner.nextLine());
                            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                                validTInput = true;
                            } else {
                                System.out.print("Amount must be greater than 0: ");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid input. Please enter a valid number: ");
                        }
                    }

                    Timestamp transaction_time = new Timestamp(System.currentTimeMillis());

                    if (tChoice == 1) {
                        // ✅ Ownership check for withdrawal
                        if (!accountController.isOwner(accountCode)) {
                            System.out.println("Error: You do not own this account.");
                            continue;
                        }
                        if (transferController.withdrawal(accountCode, amount, transaction_time)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Withdrawal failed.");
                        }
                    } else if (tChoice == 2) {
                        // ✅ Ownership check for deposit
                        if (!accountController.isOwner(accountCode)) {
                            System.out.println("Error: You do not own this account.");
                            continue;
                        }
                        if (transferController.deposit(accountCode, amount, transaction_time)) {
                            System.out.println("Deposit successful!");
                        } else {
                            System.out.println("Deposit failed.");
                        }
                    } else if (tChoice == 3) {
                        System.out.print("Enter destination account code: ");
                        String destAccount = scanner.nextLine();
                        if (transferController.transfer(accountCode, destAccount, amount, transaction_time)) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Transfer failed.");
                        }
                    }
                }
            }


        }

        scanner.close();
    }
}