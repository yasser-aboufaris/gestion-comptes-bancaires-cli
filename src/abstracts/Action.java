package abstracts;
public abstract class Action{
    protected String accountCode;
    protected int amount;
    protected void generateCode(){

    }

    protected Action(int amount, String acountCode){}

    public String getAccountCode() {
        return accountCode;
    }
    public int getAmount(){
        return amount;
    }

}