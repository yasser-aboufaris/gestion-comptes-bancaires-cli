package abstracts;
import java.math.BigDecimal;


public abstract class Account {
    protected String accountCode;
    protected int ownerId;
    protected BigDecimal balance;

    protected Account(String accountCode, int ownerId, BigDecimal balance) {
        this.accountCode = accountCode;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public String getAccountCode() { return accountCode; }
    public int getOwnerId() { return ownerId; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public void setAccountCode(String accountCode){this.accountCode = accountCode;}
    public void setOwnerId(int ownerId ){
        this.ownerId = ownerId;
    }


    public void updateBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public abstract void showDetails();
}
