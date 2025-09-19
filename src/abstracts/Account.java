package abstracts;

public abstract class Account {
    protected String accountCode;
    protected int ownerId;
    protected double balance;

    protected Account(String accountCode, int ownerId, double balance) {
        this.accountCode = accountCode;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public String getAccountCode() { return accountCode; }
    public int getOwnerId() { return ownerId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setAccountCode(String accountCode){this.accountCode = accountCode;}
    public void setOwnerId(int ownerId ){
        this.ownerId = ownerId;
    }


    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public abstract void showDetails();
}
