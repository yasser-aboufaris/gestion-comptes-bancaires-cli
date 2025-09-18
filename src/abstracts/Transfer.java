package abstracts;
public abstract class Transfer {
    protected String accountCode;
    protected int amount;
    protected void generateCode(){

    }

    protected Transfer(int amount, String acountCode){}

    public String getAccountCode() {
        return accountCode;
    }
    public int getAmount(){
        return amount;
    }

}