package abstracts;

public abstract class Transfer {
    protected String accountCode;
    protected int amount;

    protected Transfer(int amount, String accountCode) {
        this.amount = amount;
        this.accountCode = accountCode;
    }

    protected void generateCode() {
    
    }

    public String getAccountCode() {
        return accountCode;
    }

    public int getAmount() {
        return amount;
    }
}
