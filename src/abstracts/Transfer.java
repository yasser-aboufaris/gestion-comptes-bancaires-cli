package abstracts;

import java.math.BigDecimal;
import java.sql.Timestamp;

public abstract class Transfer {
    protected String accountCode;
    protected BigDecimal amount;
    protected Timestamp transaction_time;

    protected Transfer(BigDecimal amount, String accountCode, Timestamp transaction_time) {
        this.amount = amount;
        this.accountCode = accountCode;
        this.transaction_time = transaction_time;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Timestamp getTransactionTime() {
        return transaction_time;
    }
}