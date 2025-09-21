# 🌍💳 Bank Account Management System

## 📌 About the Project
This project is a **Bank Account Management System** developed as a Java 8 **console application**.  
It simulates the core operations of a real bank while following **clean architecture principles**.

The system includes:
- Object-Oriented Programming (OOP) design
- Separation of concerns across layers
- Database-backed persistence
- Practical banking operations (deposits, withdrawals, transfers, account management)

---

## 🚀 Features
- 🔑 **User Authentication** – Sign up and log in securely.
- 🏦 **Account Management** – Create and manage multiple accounts (current, savings, etc.).
- 💵 **Deposits & Withdrawals** – Perform essential banking operations.
- 🔄 **Transfers** – Send money between accounts.
- 📜 **Transaction History** – Track all operations with timestamps.
- 🗄️ **Database Integration** – Full persistence of users, accounts, and operations using SQL.
- 🌐 **Multilingual Documentation** – English, French, and Arabic.

---

## 🎯 Why this project?
Banking is one of the most relatable examples of **how data and logic interact**.  
Instead of abstract exercises, this project works with real-world concepts like **credit, debit, and account balances**, making it easier to learn **Java, OOP, and system design**.

---

## 🛠️ Tech Stack
- **Java 8** – Core programming language
- **JDBC** – For database connectivity
- **PostgreSQL/MySQL** – Database persistence
- **Console-based UI** – Lightweight and portable
- **Clean Architecture** – Separation of concerns for maintainability

---

## 📂 Project Structure
- **abstracts/** → Abstract base classes (`Account`, `Transfer`)
- **models/** → Domain models (`CheckingAccount`, `SavingAccount`, `Deposit`, `Withdrawal`, etc.)
- **controllers/** → Business logic (`AccountController`, `TransferController`, etc.)
- **utils/** → Helper classes (`DatabaseConnection`, validators, etc.)
- **Main.java** → Entry point with menus and user interaction

---

## 🔑 Example Use Case
1. A user signs up and creates a bank account.
2. They deposit money into their account.
3. They withdraw funds (validated against balance).
4. They transfer money to another account.
5. They check their full transaction history.
6. Data is stored and retrieved from the database for persistence.

---

## 🏦 Possible Enhancements
- 📈 Interest calculation for savings accounts
- 👨‍💼 Admin dashboard for managing users/accounts
- 💻 Migration to a web or GUI-based interface

---

## 📜 License
This project is licensed under the **MIT License**.  
You are free to use, modify, and distribute it.

---

<p align="center">
  💳 <strong>Banking made simple — manage accounts, track money, and practice clean architecture with a real database.</strong>
</p>
