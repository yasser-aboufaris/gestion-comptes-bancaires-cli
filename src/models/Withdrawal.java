package models;

import abstracts.Transfer;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Withdrawal extends Transfer {

    public Withdrawal(String accountCode, BigDecimal amount, Timestamp transaction_time) {
        super(amount, accountCode, transaction_time);
    }

    public void showDetails() {
        System.out.println("Deposit: $" + amount + " to " + accountCode);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "accountCode='" + accountCode + '\'' +
                ", amount=" + amount +
                ", transaction_time=" + transaction_time +
                '}';
    }
}
